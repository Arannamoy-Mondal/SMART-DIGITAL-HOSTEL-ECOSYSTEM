const loadRole = async () => {
    try {
        const response = await fetch("http://0.0.0.0:8001/role/get");
        const roles = await response.json();

        const roleSelect = document.getElementById("role");
        roleSelect.innerHTML = '<option value="" disabled selected>Select a role</option>';

        roles.forEach(roleData => {
            const option = document.createElement("option");
            option.value = roleData.id; 
            const roleName = roleData.role;
            option.textContent = roleName.charAt(0).toUpperCase() + roleName.slice(1); 
            roleSelect.appendChild(option);
        });
        console.log(roles);

    } catch (error) {
        console.error("Error loading roles:", error);
    }
}

window.onload = loadRole;