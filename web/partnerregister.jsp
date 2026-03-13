<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <title>Defence Supply Chain</title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport">
    <meta content="Free Website Template" name="keywords">
    <meta content="Free Website Template" name="description">

    <!-- Favicon -->
    <link href="img/favicon.ico" rel="icon">

    <!-- Font Awesome -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.10.0/css/all.min.css" rel="stylesheet">

    <!-- Flaticon Font -->
    <link href="lib/flaticon/font/flaticon.css" rel="stylesheet">

    <!-- Libraries Stylesheet -->
    <link href="lib/owlcarousel/assets/owl.carousel.min.css" rel="stylesheet">

    <!-- Customized Bootstrap Stylesheet -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body class="bg-black">
    <!-- Topbar Start -->
    <div class="container-fluid">
        <div class="row bg-secondary py-2 px-lg-5">
            <div class="col-lg-6 text-center text-lg-left">
                <div class="d-inline-flex align-items-center">
                    <p class="mr-2 mb-2 mb-lg-0 text-white">Opening Hours:</p>
                    <span class="mb-2 mb-lg-0 text-white">24/7</span>
                </div>
            </div>
            <div class="col-lg-6 text-center text-lg-right">
                <div class="d-inline-flex align-items-center">
                    <p class="m-0 mr-1 text-white">Follow Us:</p>
                    <a class="text-white px-2" href="">
                        <i class="fab fa-facebook-f"></i>
                    </a>
                    <a class="text-white px-2" href="">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a class="text-white px-2" href="">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                    <a class="text-white px-2" href="">
                        <i class="fab fa-instagram"></i>
                    </a>
                    <a class="text-white px-2" href="">
                        <i class="fab fa-youtube"></i>
                    </a>
                </div>
            </div>
        </div>
        <div class="row py-3 px-lg-5">
            <div class="col-lg-4">
                <a href="" class="navbar-brand d-none d-lg-block">
                    <h1 class="m-0 display-5 text-capitalize font-italic"><span class="text-primary">Safety</span>First</h1>
                </a>
            </div>
            <div class="col-lg-8 text-center text-lg-right">
                <div class="d-inline-flex align-items-center">
                    <div class="d-inline-flex flex-column text-center pr-3 border-right">
                        <h6>Our Office</h6>
                        <p class="m-0">Bengaluru</p>
                    </div>
                    <div class="d-inline-flex flex-column text-center px-3 border-right">
                        <h6>Email Us</h6>
                        <p class="m-0">info@example.com</p>
                    </div>
                    <div class="d-inline-flex flex-column text-center pl-3">
                        <h6>Call Us</h6>
                        <p class="m-0">+91 123 345 6789</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Topbar End -->


    <!-- Navbar Start -->
    <div class="container-fluid p-0 nav-bar">
        <nav class="navbar navbar-expand-lg bg-none navbar-dark py-0">
            <a href="" class="navbar-brand d-block d-lg-none">
                <h1 class="m-0 display-5 text-capitalize font-italic text-white"><span class="text-primary">Safety</span>First</h1>
            </a>
            <button type="button" class="navbar-toggler" data-toggle="collapse" data-target="#navbarCollapse">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse justify-content-between" id="navbarCollapse">
                <div class="navbar-nav m-auto py-4">
                    <a href="index.html" class="nav-item nav-link active">Home</a>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle active" data-toggle="dropdown" >Supplier</a>
                        <div class="dropdown-menu text-capitalize">
                            <a href="supplierlogin.jsp" class="dropdown-item">Login</a>
                            <a href="supplierregister.jsp" class="dropdown-item">Register</a>
                        </div>
                    </div>
                    <div class="nav-item dropdown">
                        <a href="#" class="nav-link dropdown-toggle active" data-toggle="dropdown">Partner</a>
                        <div class="dropdown-menu text-capitalize">
                            <a href="partnerlogin.jsp" class="dropdown-item">Login</a>
                            <a href="partnerregister.jsp" class="dropdown-item">Register</a>
                        </div>
                    </div>
                  
                    <a href="mbdalogin.jsp" class="nav-item nav-link active">MBDA Login</a>
                   
                    <a href="contact.html" class="nav-item nav-link active">Contact</a>
                </div>
            </div>
        </nav>
    </div>
    <!-- Navbar End -->

    <!-- Carousel Start -->
   
    <!-- Carousel End -->


    <!-- Features Start -->
   
    <!-- Features End -->


    <!-- About Start -->
   
    <!-- About End -->

    <div><br></div>
    <!-- Services Start -->
    <div class="container pt-5">
        <div class="d-flex flex-column text-center mb-5">
           
            <h1 class="m-0">PARTNER REGISTRATION FORM</h1>
            
            <br>
    <%
    String msg=request.getParameter("msg");
    if (msg!=null)
    {
        if (msg.equals("ALREADY"))
        {
            %>
            <script>
                alert("user Name already exist!");
                </script>
            <%
        }
        else
        if (msg.equals("SUCCESS"))
        {
         %>
            <script>
                alert("Partner registered successfully!");
                </script>
            <% 
        }
        else
        {
            %>
            <script>
                alert("Supplier registration failed!");
                </script>
            <%
        }
    }
    %>
    
    <form name="partnerregister" method="post" action="partnerregister">
    <table align="center" width="600" height="120">
        
        <tr>
            <td>Partner Name</td>
            <td> <input type=text name=sname required/></td>
        </tr>
        
        
        
        <tr>
            <td>Address</td>
            <td> <textarea name=address required ></textarea></td>
        </tr>
        
        <tr>
            <td>Contact No.</td>
            <td> <input type=number name=mno required/></td>
        </tr>
        
        <tr>
            <td>Email ID</td>
            <td> <input type=email name=email required/></td>
        </tr>
       
   
        <tr>
            <td>USER NAME</td>
            <td> <input type=text name=uname required/></td>
        </tr>
        <tr>
            <td>PASSWORD</td>
            <td> <input type=password name=pass required/></td>
        </tr>
        <tr>
            <td></td>
            <td>
                <input type="submit" name="Register" value="Register" style="height:30px; width:100px"/>
            <input type="reset" name="reset" value="Reset" style="height:30px; width:100px"/>
            </td>
            
        </tr>
    </table>
</form>
            
        </div>
        
            
            
           
         
        </div>
    </div>
    <!-- Services End -->


    <!-- Facts Start -->
      <!-- Facts End -->


    <!-- Features Start -->
   
    <!-- Features End -->


    <!-- Subscribe Start -->
  
    <!-- Subscribe End -->


    <!-- Team Start -->
   
    <!-- Team End -->


    <!-- Testimonial Start -->
  
    <!-- Testimonial End -->


    <!-- Blog Start -->
  
    <!-- Blog End -->


    <!-- Footer Start -->
   
    <!-- Footer End -->


    <!-- Back to Top -->
    <a href="#" class="btn btn-secondary border back-to-top"><i class="fa fa-angle-double-up"></i></a>


    <!-- JavaScript Libraries -->
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js"></script>
    <script src="lib/easing/easing.min.js"></script>
    <script src="lib/waypoints/waypoints.min.js"></script>
    <script src="lib/counterup/counterup.min.js"></script>
    <script src="lib/owlcarousel/owl.carousel.min.js"></script>

    <!-- Contact Javascript File -->
    <script src="mail/jqBootstrapValidation.min.js"></script>
    <script src="mail/contact.js"></script>

    <!-- Template Javascript -->
    <script src="js/main.js"></script>
</body>

</html>