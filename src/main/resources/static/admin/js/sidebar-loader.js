
window.onload = async function(){

    const response =
        await fetch("/admin/sidebar.html");

    const sidebar =
        await response.text();

    document.getElementById(
        "sidebar-container"
    ).innerHTML = sidebar;

   document.getElementById(
       "manageFlights"
   ).classList.add("active");

    loadFlights();
}

function callManageAirport()
{
  window.location.href =
              "/admin/manage_airport.html";
}

function callBookingManagement()
{
  window.location.href =
              "/admin/manage_bookings.html";
}

async function loadFlights(){

    const response =
        await fetch(
            "http://localhost:8080/flight/get-all"
        );

    const flights =
        await response.json();

    displayFlights(flights);
}
function displayFlights(flights){

    const tbody =
        document.getElementById(
            "flightTableBody"
        );

    tbody.innerHTML = "";

    flights.forEach(flight => {

        tbody.innerHTML += `

            <tr>

                <td>${flight.id}</td>

                <td>${flight.source}</td>

                <td>${flight.destination}</td>

                <td>${flight.departureTime}</td>

                <td>

                    <span
                        class="status-badge">

                        ${flight.status}

                    </span>

                </td>

                <td>

                    <button
                        class="btn btn-primary btn-sm"
                        onclick="editFlight(
                            '${flight.id}'
                        )">

                        Edit

                    </button>

                    <button
                        class="btn btn-danger btn-sm"
                        onclick="deleteFlight(
                            '${flight.id}'
                        )">

                        Delete

                    </button>

                </td>

            </tr>

        `;
    });
}
async function deleteFlight(id){

    if(
        !confirm(
            "Delete Flight?"
        )
    ){
        return;
    }

    await fetch(
        `http://localhost:8080/flight/delete?id=${id}`,
        {
            method:"DELETE"
        }
    );

    loadFlights();
}
function openAddFlight(){

    window.location.href =
        "/admin/add-flight.html";
}
