import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from 'src/app/services/user.service';
import Swal from 'sweetalert2';


@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  constructor(private userService:UserService, private snack:MatSnackBar) { }

  public user={ 
    userName: '',
    password: '',
    firstName: '',
    lastName: '',
    email: '',
    phone: ''
  };

  ngOnInit(): void {
  }

  formSubmit(): void{
    console.log(this.user);
    var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
    
    if(!emailPattern.test(this.user.email)){
      this.snack.open('enter correct email!', '', {
        duration:3000
      });
      return;
    }

    var passwordPattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$/;
    if(!passwordPattern.test(this.user.password)){
      this.snack.open('Password Must Contain 8 letters, One Uppercase, One Lowercase, one number and one special character', '', {
        duration:3000
      });
      return;
    }

    /*
    var phonePattern = /^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]\d{3}[\s.-]\d{4}$/;

    if(!phonePattern.test(this.user.phone)){
      this.snack.open('Enter a valid phone number', '',{
        duration:3000
      });
      return;
    }
    */

    if(this.user.userName=="" || this.user.userName==null){
      this.snack.open('User is required!!', '', {
        duration:3000,
      });
      return;
    }



    this.userService.addUser(this.user).subscribe(
      (data) => {
        console.log(data);
        Swal.fire({
          position: 'center',
          icon: 'success',
          title: 'User is registered',
          timer: 1500
        });
      },
      (error) => {
        console.log(error);
        //alert('something went wrong');
        this.snack.open('something went wrong', '', {
          duration:3000
        });
      }
    )
  }


  



}
