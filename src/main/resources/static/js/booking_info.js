window.onload = function(){

    document.getElementById(
        "flightId"
    ).innerText =
        localStorage.getItem(
            "flightId"
        );

    document.getElementById(
        "source"
    ).innerText =
        localStorage.getItem(
            "source"
        );

    document.getElementById(
        "destination"
    ).innerText =
        localStorage.getItem(
            "destination"
        );

    document.getElementById(
        "travelDate"
    ).innerText =
        localStorage.getItem(
            "travelDate"
        );

    document.getElementById(
        "seatNo"
    ).innerText =
        localStorage.getItem(
            "seatNo"
        );

    document.getElementById(
        "seatClass"
    ).innerText =
        localStorage.getItem(
            "seatClass"
        );

    document.getElementById(
        "seatPrice"
    ).innerText =
        localStorage.getItem(
            "seatPrice"
        );
}
async function confirmBooking(){
try{
console.log("Function Started");

    const bookingRequest = {

        flightId:
            localStorage.getItem(
                "flightId"
            ),

        firstName:
            document.getElementById(
                "firstName"
            ).value,

        lastName:
            document.getElementById(
                "lastName"
            ).value,

        age:
            document.getElementById(
                "age"
            ).value,

        gender:
            document.getElementById(
                "gender"
            ).value,

        email:
            document.getElementById(
                "email"
            ).value,
         seatId:
             localStorage.getItem(
                 "seatId"
             ),
        mobile:
            document.getElementById(
                "mobile"
            ).value
    };

     const response = await fetch(
        "http://localhost:8080/booking/save",
        {
            method:"POST",

            headers:{
                "Content-Type":
                "application/json"
            },

            body:JSON.stringify(
                bookingRequest
            )
        }
    );
   console.log("Fetch Completed");

     const booking = await response.json();


            console.log(booking.id);

    if(response.ok){
console.log("Redirecting...");
           window.location.href =
               `/booking_success.html?bookingId=${booking.id}`;

        }
        else
        {
    alert("Something Went Wrong ! Please Try Again");
    }
    }
    catch(error){

                 console.error("ERROR:", error);

                 alert(error);
             }
}