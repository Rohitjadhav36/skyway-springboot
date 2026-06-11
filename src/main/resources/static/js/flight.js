async function searchFlights() {

    const source =
        document.getElementById("source").value;


    const destination =
        document.getElementById("destination").value;

    try {

        const response = await fetch(
            `http://localhost:8080/flight/search?source=${source}&destination=${destination}`
        );

        const flights =
            await response.json();

        displayFlights(flights);

    } catch(error) {

        console.error(error);

        alert("Unable to fetch flights");
    }
}

function displayFlights(flights) {

    const container =
        document.getElementById("flightResults");

    container.innerHTML = "";

    if (flights.length === 0) {

        container.innerHTML =
            "<h4 class='text-white'>No Flights Found</h4>";

        return;
    }

    flights.forEach(flight => {

        container.innerHTML += `

        <div class="flight-card">

            <div class="flight-id">
                ${flight.id}
            </div>

            <div class="flight-info">

                <h4>
                    ✈ ${flight.source}
                    →
                    ${flight.destination}
                </h4>

                <p>
                    📅 ${flight.date}
                    · ${flight.departureTime}
                    · Status: ${flight.status}
                </p>

            </div>

            <div>

                <button
                onclick="bookFlight('${flight.id}')"
                    class="btn btn-warning">
                    Book Now
                </button>

            </div>

        </div>

        `;
    });
}

window.onload = function () {

    loadDefaultFlights();

};

async function loadDefaultFlights() {

    const response = await fetch(
        "http://localhost:8080/flight/default"
    );

    const flights = await response.json();

    displayFlights(flights);
}
function bookFlight(flightId) {

    window.location.href =
        `/show_seat.html?flightId=${flightId}`;
}