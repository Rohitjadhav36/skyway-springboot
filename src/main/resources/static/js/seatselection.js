
let selectedSeatId = null;
let flightId = null;

window.onload = function () {

    const params = new URLSearchParams(window.location.search);

    flightId = params.get("flightId");

    loadFlightSummary();
    loadAvailableSeats();
};

async function loadFlightSummary() {

    try {

        const response = await fetch(
            `http://localhost:8080/flight/get-by/${flightId}`
        );

        const flight = await response.json();

        document.getElementById("flightNo").innerText =
            flight.id;

        document.getElementById("source").innerText =
            flight.source;

        document.getElementById("destination").innerText =
            flight.destination;

        document.getElementById("travelDate").innerText =
            flight.date;

        document.getElementById("departureTime").innerText =
            flight.departureTime;

         document.getElementById("arrivalTime").innerText =
                        flight.arrivalTime;

// Calculating Traveling Time
        const departure =
            new Date(`1970-01-01T${flight.departureTime}`);

        const arrival =
            new Date(`1970-01-01T${flight.arrivalTime}`);

        const diffMs =
            arrival - departure;

        const hours =
            Math.floor(diffMs / (1000 * 60 * 60));

        const minutes =
            Math.floor(
                (diffMs % (1000 * 60 * 60))
                / (1000 * 60)
            );

        document.getElementById("travelingTime").innerText =
            `${hours}h ${minutes}m`;

            //Set Seat Price
         document.getElementById("economyClass").innerText =
                   flight.economyClassPrice;

          document.getElementById("businessClass").innerText =
                             flight.businessClassPrice;

           document.getElementById("firstClass").innerText =
                              flight.firstClassPrice;



    } catch (error) {

        console.error(error);

        alert("Unable to load flight details");
    }
}

async function loadAvailableSeats() {

    try {

        const response = await fetch(
            `http://localhost:8080/flight/available-seats/${flightId}`
        );

        const seats = await response.json();

        displaySeats(seats);

    } catch (error) {

        console.error(error);

        alert("Unable to load seats");
    }
}

function displaySeats(seats) {

    const container =
        document.getElementById(
            "seatContainer"
        );

    container.innerHTML = "";

    seats.forEach(seat => {

        let seatClass =
            seat.isBooked
            ? "booked"
            : "available";

        container.innerHTML += `

            <button
                class="seat ${seatClass}"
                ${seat.isBooked
                    ? "disabled"
                    : ""}
                onclick="selectSeat(
                    ${seat.id},
                    '${seat.seatNo}',
                    this
                )">

                ${seat.seatNo}

            </button>

        `;
    });
}

function selectSeat(id, seatNo) {

    selectedSeatId = id;

    document.getElementById(
        "selectedSeat"
    ).innerText = seatNo;
}

async function confirmBooking() {

    if (!selectedSeatId) {

        alert("Please select a seat");

        return;
    }

    const bookingRequest = {

        flightId: flightId,

        seatId: selectedSeatId
    };

    try {

        const response = await fetch(

            "http://localhost:8080/booking/create",

            {
                method: "POST",

                headers: {
                    "Content-Type":
                        "application/json"
                },

                body: JSON.stringify(
                    bookingRequest
                )
            }
        );

        const result =
            await response.json();

        alert(
            "Booking Successful"
        );

    } catch (error) {

        console.error(error);

        alert(
            "Booking Failed"
        );
    }
}