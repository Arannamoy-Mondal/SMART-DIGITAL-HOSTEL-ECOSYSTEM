const token = localStorage.getItem("jwtToken");

fetch("http://0.0.0.0:8001/some-secure-api", {
    method: "GET",
    headers: {
        "Content-Type": "application/json",
        "Authorization": `Bearer ${token}` 
    }
})