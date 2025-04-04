<!DOCTYPE html>
<html>
<head>
  <title>University Management</title>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Arial">
  <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Montserrat">
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
  <style>
    .w3-sidebar a {font-family: "Arial",sans-serif}
    body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
    img:hover {
      cursor: pointer;"src/main/webapp/index.jsp"
    }
    button:hover {
      cursor: pointer;
    }
    .horizontal-menu {
      overflow: hidden;
      background-color: #333;
    }
    .horizontal-menu a {
      float: left;
      display: block;
      color: #f2f2f2;
      text-align: center;
      padding: 14px 16px;
      text-decoration: none;
    }
    .horizontal-menu a:hover {
      background-color: #ddd;
      color: black;
    }
    .horizontal-menu a.active {
      background-color: #4CAF50;
      color: white;
    }
    body {
    font-family: Arial, sans-serif;
  }
  .horizontal-menu {
    overflow: hidden;
    background-color: #333;
  }
  .horizontal-menu a, .dropdown button {
    float: left;
    display: block;
    color: white;
    text-align: center;
    padding: 14px 16px;
    text-decoration: none;
  }
  .horizontal-menu a:hover, .dropdown button:hover, .dropdown button:focus {
    background-color: #ddd;
    color: black;
  }
  .horizontal-menu .dropdown {
    float: left;
    overflow: hidden;
  }
  .horizontal-menu .dropdown-content {
    display: none;
    position: absolute;
    background-color: #f9f9f9;
    min-width: 160px;
    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
    z-index: 1;
  }
  .horizontal-menu .dropdown-content a {
    float: none;
    color: black;
    padding: 12px 16px;
    text-decoration: none;
    display: block;
    text-align: left;
  }
  .horizontal-menu .dropdown-content a:hover {
    background-color: #ddd;
  }
  .dropdown:hover .dropdown-content {
    display: block;
  }
  
  </style>
</head>
<body class="w3-content" style="max-width:1200px;">

<!-- Top menu -->
<div class="w3-bar w3-top  w3-xlarge" style="padding: 10px;">
  <!-- <img src="https://www.mbacollegesbangalore.in/wp-content/uploads/2017/10/Presidency-University.png" alt="" width="70" height="70" style="margin-right: 10px; padding-left: 10px;" > -->
   <!-- <div>
    <p style="color: rgb(16, 15, 15); padding-left: 10px;">Gain More Knowledge, Reach Greater Heights</p>
  </div> -->
  <!-- <div style="margin-left: auto;">
    <button class="w3-button w3-white w3-border w3-border-white" style="margin-right: 10px;">Sign In</button>
    <button class="w3-button w3-black" style="border-radius: 5px;">Sign Up</button>
  </div> -->
</div>
<!-- Horizontal menu -->
<div class="horizontal-menu">
  <a class="active" href="#">Home</a>
  <a href="#">User Management</a>
  <a href="#">Student Module</a>
  <div class="dropdown">
    <button id="financeButton">Finance Module</button>
    <div id="financeDropdown" class="dropdown-content">
      <a href="register.html">Student Form</a>
      <a href="payment/paymentform">Payment Form</a>
      <a href="scholarship/scholarshipform">Scholarship Form</a>
      <a href="course/courseform">Course Form</a>
      <a href="fees/studentform">Fees Form</a>
    </div>
     <a href="#">Class Schedules</a>
  <a href="#">Library Module</a>
  <a href="#">Course Module</a>
  </div>
</div>

<!-- Image header -->
<div class="w3-display-container w3-container">
  <img src="https://i.redd.it/guys-presidency-university-se-cse-worth-it-hain-v0-301eves3wtra1.jpg?width=1916&format=pjpg&auto=webp&s=6403d7eb00c18a48bdd014aca5b2d90fb3a305c0" alt="Presidency University" style="width:100%", height="100%">
  <div class="w3-display-topleft w3-text-white" style="padding:24px 48px;">
  </div>
</div>

<marquee behavior="" direction="" style="margin-top: 40px; font-size: 21px; color: rgb(0, 0, 0);">Presidency University,, Ranked 5th Top emerging Engineering Institute of India !!!!!</marquee>







   
  

  
  <!-- Other grid items -->



<!-- Subscribe section -->

<!-- Footer -->
<footer class="w3-padding-64 w3-light-blue w3-small w3-center" id="footer" >
    <div class="w3-row-padding">
      <div class="w3-col s12 " >
        
              <div class="w3-col s4">
                <h4>About</h4>
                <p><a href="#">Vision And Mission </a></p>
                <p><a href="#">Accolades</a></p>
                <p><a href="#">Chancellor's Message</a></p>
                <p><a href="#">University Officers</a></p>
                <p><a href="#">Policy Documents</a></p>
                <p><a href="#">Governing Body</a></p>
                <p><a href="#">Approval Documents</a></p>
                <p><a href="#">Accreditation</a></p>
                <p><a href="#">Committees</a></p>
              </div>

              <div class="w3-col s4 w3-justify" style=" margin-top: 40px;  gap: 20px; justify-content: center; margin-left: 10px;"  >
                <h4>Presidency University</h4>
                <p><i class="fa fa-fw fa-phone"></i>111190909</p>
                <p><i class="fa fa-fw fa-envelope"></i> presidency@gmail.com</p>
                <p><i class="fa fa-fw fa-map-marker"></i>Itgalpura, Yelahanka, Bangalore,560063</p>

                <br>
                        <i class="fa fa-facebook-official w3-hover-opacity w3-large"></i>
                        <i class="fa fa-instagram w3-hover-opacity w3-large"></i>
                        <i class="fa fa-snapchat w3-hover-opacity w3-large"></i>
                        <i class="fa fa-pinterest-p w3-hover-opacity w3-large"></i>
                        <i class="fa fa-twitter w3-hover-opacity w3-large"></i>
                        <i class="fa fa-linkedin w3-hover-opacity w3-large"></i>
                      </div>
            </footer>
        
              <div class="w3-col s4 w3-justify" style=" margin-top: 40px;  gap: 20px; justify-content: center; margin-left: 10px;"  >
                <h4>Presidency University</h4>
                <p><i class="fa fa-fw fa-phone"></i>111190909</p>
                <p><i class="fa fa-fw fa-envelope"></i> presidency@gmail.com</p>
                <p><i class="fa fa-fw fa-map-marker"></i>Itgalpura, Yelahanka, Bangalore,560063</p>

                <br>
                        <i class="fa fa-facebook-official w3-hover-opacity w3-large"></i>
                        <i class="fa fa-instagram w3-hover-opacity w3-large"></i>
                        <i class="fa fa-snapchat w3-hover-opacity w3-large"></i>
                        <i class="fa fa-pinterest-p w3-hover-opacity w3-large"></i>
                        <i class="fa fa-twitter w3-hover-opacity w3-large"></i>
                        <i class="fa fa-linkedin w3-hover-opacity w3-large"></i>
                      </div>
                    </div>
                  
            </div>
          </footer>
          <button type="submit" class="w3-button w3-block w3-grey"> Submitted by Group 5</button>
        </form>
      </div>

      
      
      </div>
    </div>
  </footer>
  </div>
</footer>

<div class="w3-grey w3-center ">© Presidency University. All Rights Reserved.</div>

<!-- End page content -->
</body>
</html>