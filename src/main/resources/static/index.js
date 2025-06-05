async function alta(nombre) {
    const resp = await fetch("/mascotas", {
        method: "POST",
        headers: {
            "Content-type": "application/json"
        },
        body: JSON.stringify({nombre: nombre})
    });
    if (resp.ok) cargarLista(); else console.error("Error en alta");
}

async function cargarLista() {
    const resp = await fetch("/mascotas");
    if (!resp.ok) {
        console.error("Error al cargar las mascotas");
        return;
    }
    const datos = await resp.json();
    const lista = document.getElementById("mascotas");
    lista.innerHTML = datos.map(m => `<li>${m.nombre}</li>`).join("");
}

document.getElementById("btnAlta").addEventListener("click", () => {
    const nombre = document.getElementById("nombreNuevaMascota").value.trim();
    if (nombre) alta(nombre);
});

document.addEventListener("DOMContentLoaded", cargarLista);