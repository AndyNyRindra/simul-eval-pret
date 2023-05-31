const onRestoreButtonClicked = (id, name, url) => {
    document.getElementById("restore-id").textContent = id;
    document.getElementById("restore-url").addEventListener('click', function() {
        sendGet(url, null);
    });
    document.getElementById("restore-name").textContent = name;
}