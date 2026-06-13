window.onload = function(){
const params =
    new URLSearchParams(
        window.location.search
    );

const bookingId =
    params.get("bookingId");

console.log("Booking ID:", bookingId);
     loadBookingDetails(bookingId);
}

async function loadBookingDetails(bookingId)
{
 try {

        const response = await fetch(
            `http://localhost:8080/booking/get-info-by/${bookingId}`
        );

       const bookingResponse = await response.json();

         console.log(bookingResponse);

        document.getElementById("source").innerText =
            bookingResponse.flight.source;

        document.getElementById("flightNo").innerText =
            bookingResponse.flight.id;

        document.getElementById("destination").innerText =
            bookingResponse.flight.destination;

        document.getElementById("seatNo").innerText =
            bookingResponse.seat.seatNo;

        document.getElementById("passenger").innerText =
            bookingResponse.passenger.firstName +
            " " +
            bookingResponse.passenger.lastName;

         document.getElementById("age").innerText = bookingResponse.passenger.age;

         document.getElementById("seatNo").innerText = bookingResponse.seat.seatNo;

         document.getElementById("seatClass").innerText = bookingResponse.seat.seatClass;

        let seatPrice;

        if(bookingResponse.seat.seatClass === "ECONOMY")
        {
            seatPrice =
                bookingResponse.flight.economyClassPrice;
        }
        else if(bookingResponse.seat.seatClass === "BUSINESS")
        {
            seatPrice =
                bookingResponse.flight.businessClassPrice;
        }
        else
        {
            seatPrice =
                bookingResponse.flight.firstClassPrice;
        }

        document.getElementById("price").innerText = seatPrice;

        }
        catch(error)
        {
        console.error(error);

                alert("Unable to load Booking details");

        }

}