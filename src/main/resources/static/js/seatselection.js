
let selectedSeatId = null;
let flightId = null;
let currentFlight = null;
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

       currentFlight = await response.json();

       const flight = currentFlight;

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
            `http://localhost:8080/flight/get-all-seat/${flightId}`
        );

        const seats = await response.json();

const economySeats =
    seats.filter(
        seat => seat.seatClass === "ECONOMY"
    );

    const businessSeats =
        seats.filter(
            seat => seat.seatClass === "BUSINESS"
        );

const firstClassSeats =
    seats.filter(
        seat => seat.seatClass === "FIRST"
    );

        displaySeats(economySeats,businessSeats,firstClassSeats);

    } catch (error) {

        console.error(error);

        alert("Unable to load seats");
    }
}

function displaySeats(economySeats,businessSeats,firstClassSeats) {

    const economyClassContainer =
        document.getElementById(
            "economyClassSeatContainer"
        );

    economyClassContainer.innerHTML = "";

    economySeats.forEach(seat => {

        let seatClass =
            seat.isBooked
            ? "booked"
            : "available";

        economyClassContainer.innerHTML += `

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

    const businessClassContainer =
            document.getElementById(
                "businessClassSeatContainer"
            );

        businessClassContainer.innerHTML = "";

        businessSeats.forEach(seat => {

            let seatClass =
                seat.isBooked
                ? "booked"
                : "available";

            businessClassContainer.innerHTML += `

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

      const firstClassContainer =
              document.getElementById(
                  "firstClassSeatContainer"
              );

          firstClassContainer.innerHTML = "";

          firstClassSeats.forEach(seat => {
              console.log(seat);
              let seatClass =
                  seat.isBooked
                  ? "booked"
                  : "available";

              firstClassContainer.innerHTML += `

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

function selectSeat(id, seatNo, button) {

    selectedSeatId = id;

    document.getElementById(
        "selectedSeat"
    ).innerText = seatNo;

    // Remove selected color from all seats

    document
        .querySelectorAll(".seat")
        .forEach(seat => {
            seat.classList.remove(
                "selected"
            );
        });

    // Add selected color to clicked seat

    button.classList.add(
        "selected"
    );
}

async function fillBookingInfo() {

    if(selectedSeatId == null){

        alert("Please select a seat");

        return;
    }

    try {

        const response = await fetch(
            `http://localhost:8080/flight/get-seat-by/${selectedSeatId}`
        );

        const selectedSeatInfo =
            await response.json();

        localStorage.setItem(
            "flightId",
            currentFlight.id
        );

        localStorage.setItem(
            "source",
            currentFlight.source
        );

        localStorage.setItem(
            "destination",
            currentFlight.destination
        );

        localStorage.setItem(
            "travelDate",
            currentFlight.date
        );

        localStorage.setItem(
            "departureTime",
            currentFlight.departureTime
        );

        localStorage.setItem(
            "arrivalTime",
            currentFlight.arrivalTime
        );

        localStorage.setItem(
            "seatId",
            selectedSeatInfo.id
        );

        localStorage.setItem(
            "seatNo",
            selectedSeatInfo.seatNo
        );

        localStorage.setItem(
            "seatClass",
            selectedSeatInfo.seatClass
        );

        if(selectedSeatInfo.seatClass === "ECONOMY") {

            localStorage.setItem(
                "seatPrice",
                currentFlight.economyClassPrice
            );

        } else if(selectedSeatInfo.seatClass === "BUSINESS") {

            localStorage.setItem(
                "seatPrice",
                currentFlight.businessClassPrice
            );

        } else {

            localStorage.setItem(
                "seatPrice",
                currentFlight.firstClassPrice
            );
        }

        window.location.href =
            "/confirm_booking.html";

    } catch (error) {

        console.error(error);

        alert("Unable to load booking page");
    }
}