$(function () {
    let now = new Date();
    let minDate = now.toISOString().substring(0,10);
    document.getElementById('departure').setAttribute("min", minDate);
});
