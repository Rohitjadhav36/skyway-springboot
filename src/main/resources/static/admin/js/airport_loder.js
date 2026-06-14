async function loadSidebar() {

    const response =
        await fetch("/admin/sidebar.html");

    const sidebar =
        await response.text();

    document.getElementById(
        "sidebar-container"
    ).innerHTML = sidebar;

    document.getElementById(
        "manageAirports"
    ).classList.add("active");
}

function callAdminDashBoard()
{
  window.location.href =
              "/admin/admin_dashboard.html";
}

function callBookingManagement()
{
  window.location.href =
              "/admin/manage_bookings.html";
}
window.onload = async function() {

    await loadSidebar();

    loadAirports();
}
async function loadAirports(){

    const response =
        await fetch(
            "http://localhost:8080/airport/get-all"
        );

    const airports =
        await response.json();

    displayAirports(
        airports
    );
}
async function saveAirport(){

    const airportRequest = {

        code:
            document.getElementById(
                "airportCode"
            ).value,

        name:
            document.getElementById(
                "airportName"
            ).value
    };

    await fetch(
        "http://localhost:8080/airport/save",
        {
            method:"POST",

            headers:{
                "Content-Type":
                    "application/json"
            },

            body:JSON.stringify(
                airportRequest
            )
        }
    );

    loadAirports();
}
function displayAirports(airports){

    const container =
        document.getElementById(
            "airportContainer"
        );

    container.innerHTML = "";

    document.getElementById(
        "airportCount"
    ).innerText =
        airports.length + " airports";

    airports.forEach(airport => {

        container.innerHTML += `

            <div class="col-md-4 mb-4">

                <div class="airport-card">

                    <div>

                        <h1>
                            ${airport.code}
                        </h1>

                        <p>
                            ${airport.name}
                        </p>

                    </div>

                    <button
                        class="delete-btn"
                        onclick="deleteAirport(
                            ${airport.id}
                        )">

                        ✖

                    </button>

                </div>

            </div>

        `;
    });
}