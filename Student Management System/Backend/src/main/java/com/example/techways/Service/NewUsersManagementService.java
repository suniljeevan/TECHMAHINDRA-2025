package com.example.techways.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.techways.DTO.RequestResponse;
import com.example.techways.Models.NewUsers;
import com.example.techways.Repository.NewUsersRepository;

@Service
public class NewUsersManagementService {

    @Autowired
    private NewUsersRepository newUsersRepository;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public RequestResponse registerUser(RequestResponse registrationRequest) {
        RequestResponse response = new RequestResponse();

        try {
            NewUsers users = new NewUsers();
            users.setName(registrationRequest.getName());
            users.setEmail(registrationRequest.getEmail());
            users.setRole(registrationRequest.getRole());
            users.setPassword(passwordEncoder.encode(registrationRequest.getPassword()));

            NewUsers userResult = newUsersRepository.save(users);

            if (userResult.getId() > 0) {
                response.setUsers(userResult);
                response.setStatusCode(200);
                response.setMessage("User registered successfully");
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while registering user: " + e.getMessage());
            response.setError(e.getMessage());
        }
        return response;
    }

    public RequestResponse login(RequestResponse loginRequest) {
        RequestResponse response = new RequestResponse();
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                            loginRequest.getPassword()));
            var user = newUsersRepository.findByEmail(loginRequest.getEmail()).orElseThrow();
            var jwt = jwtUtils.generateToken(user);
            var refreshToken = jwtUtils.generateRefreshToken(new HashMap<>(), user);
            response.setStatusCode(200);
            response.setToken(jwt);
            response.setRole(user.getRole());
            response.setRefreshToken(refreshToken);
            response.setExpirationTime("24Hrs");
            response.setMessage("Successfully Logged In");

        } catch (AuthenticationException e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
        }
        return response;
    }

    public RequestResponse refreshToken(RequestResponse refreshTokenReqiest) {
        RequestResponse response = new RequestResponse();
        try {
            String ourEmail = jwtUtils.extractUsername(refreshTokenReqiest.getToken());
            NewUsers users = newUsersRepository.findByEmail(ourEmail).orElseThrow();
            if (jwtUtils.isTokenValid(refreshTokenReqiest.getToken(), users)) {
                var jwt = jwtUtils.generateToken(users);
                response.setStatusCode(200);
                response.setToken(jwt);
                response.setRefreshToken(refreshTokenReqiest.getToken());
                response.setExpirationTime("24Hr");
                response.setMessage("Successfully Refreshed Token");
            }
            response.setStatusCode(200);
            return response;

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage(e.getMessage());
            return response;
        }
    }

    public RequestResponse getAllUsers() {
        RequestResponse RequestResponse = new RequestResponse();

        try {
            List<NewUsers> result = newUsersRepository.findAll();
            if (!result.isEmpty()) {
                RequestResponse.setUsersList(result);
                RequestResponse.setStatusCode(200);
                RequestResponse.setMessage("Successful");
            } else {
                RequestResponse.setStatusCode(404);
                RequestResponse.setMessage("No users found");
            }
            return RequestResponse;
        } catch (Exception e) {
            RequestResponse.setStatusCode(500);
            RequestResponse.setMessage("Error occurred: " + e.getMessage());
            return RequestResponse;
        }
    }

    public RequestResponse getUsersById(Integer id) {
        RequestResponse RequestResponse = new RequestResponse();
        try {
            NewUsers usersById = newUsersRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("User Not found"));
            RequestResponse.setUsers(usersById);
            RequestResponse.setStatusCode(200);
            RequestResponse.setMessage("Users with id '" + id + "' found successfully");
        } catch (Exception e) {
            RequestResponse.setStatusCode(500);
            RequestResponse.setMessage("Error occurred: " + e.getMessage());
        }
        return RequestResponse;
    }

    public RequestResponse deleteUser(Integer userId) {
        RequestResponse RequestResponse = new RequestResponse();
        try {
            Optional<NewUsers> userOptional = newUsersRepository.findById(userId);
            if (userOptional.isPresent()) {
                newUsersRepository.deleteById(userId);
                RequestResponse.setStatusCode(200);
                RequestResponse.setMessage("User deleted successfully");
            } else {
                RequestResponse.setStatusCode(404);
                RequestResponse.setMessage("User not found for deletion");
            }
        } catch (Exception e) {
            RequestResponse.setStatusCode(500);
            RequestResponse.setMessage("Error occurred while deleting user: " + e.getMessage());
        }
        return RequestResponse;
    }

    public RequestResponse updateUser(Integer userId, NewUsers updatedUser) {
        RequestResponse RequestResponse = new RequestResponse();
        try {
            Optional<NewUsers> userOptional = newUsersRepository.findById(userId);
            if (userOptional.isPresent()) {
                NewUsers existingUser = userOptional.get();
                existingUser.setEmail(updatedUser.getEmail());
                existingUser.setName(updatedUser.getName());
                existingUser.setRole(updatedUser.getRole());

                // Check if password is present in the request
                if (updatedUser.getPassword() != null && !updatedUser.getPassword().isEmpty()) {
                    // Encode the password and update it
                    existingUser.setPassword(passwordEncoder.encode(updatedUser.getPassword()));
                }

                NewUsers savedUser = newUsersRepository.save(existingUser);
                RequestResponse.setUsers(savedUser);
                RequestResponse.setStatusCode(200);
                RequestResponse.setMessage("User updated successfully");
            } else {
                RequestResponse.setStatusCode(404);
                RequestResponse.setMessage("User not found for update");
            }
        } catch (Exception e) {
            RequestResponse.setStatusCode(500);
            RequestResponse.setMessage("Error occurred while updating user: " + e.getMessage());
        }
        return RequestResponse;
    }

    public RequestResponse getMyInfo(String email) {
        RequestResponse RequestResponse = new RequestResponse();
        try {
            Optional<NewUsers> userOptional = newUsersRepository.findByEmail(email);
            if (userOptional.isPresent()) {
                RequestResponse.setUsers(userOptional.get());
                RequestResponse.setStatusCode(200);
                RequestResponse.setMessage("successful");
            } else {
                RequestResponse.setStatusCode(404);
                RequestResponse.setMessage("User not found for update");
            }

        } catch (Exception e) {
            RequestResponse.setStatusCode(500);
            RequestResponse.setMessage("Error occurred while getting user info: " + e.getMessage());
        }
        return RequestResponse;

    }
}
