import axios from "axios"

class UserService {
    static BASR_URL = "http://localhost:8083"

    static async handelLogin(email, password) {
        try {
            const response = await axios.post(`${UserService.BASR_URL}/login`, { email, password })
            if (response.status === 200) {
                return response.data
            }

        } catch (error) {
            console.log("Error in login", error)
            return error.response.data
        }

    }
}

export default UserService