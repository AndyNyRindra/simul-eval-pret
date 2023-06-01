const onPayButtonClicked = (url, amount) => {
    document.getElementById("accept-amount").textContent = amount;
    document.getElementById("accept-form").action = url;
}