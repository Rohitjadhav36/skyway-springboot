
function renderBookings() {
  const el = document.getElementById('bookings-list');
  el.innerHTML = bookings.map(b => `
    <div class="booking-row">
      <div class="booking-id">${b.id}</div>
      <div class="booking-info">
        <div class="booking-route">${b.passenger} — ${b.route}</div>
        <div class="booking-meta">📅 ${b.date} · Seat ${b.seats} · ${b.amount}</div>
      </div>
      <span class="${b.status==='Confirmed'?'badge-booked':'badge-cancelled'}">${b.status}</span>
    </div>
  `).join('');
}
async function loadSidebar() {

    const response =
        await fetch("/admin/sidebar.html");

    const sidebar =
        await response.text();

    document.getElementById(
        "sidebar-container"
    ).innerHTML = sidebar;

    document.getElementById(
        "viewBookings"
    ).classList.add("active");
}

function callAdminDashBoard()
{
  window.location.href =
              "/admin/admin_dashboard.html";
}

function callManageAirport()
{
  window.location.href =
              "/admin/manage_airport.html";
}

window.onload = async function() {

    await loadSidebar();
  renderBookings();

}

