//funcion para si se cierra sesion en una ventana, automaticamente en las otras se recarga la pagina, mandando todas al login
            function logoutAllTabs() {
                // lanza un evento que escucharán todas las pestañas abiertas
                localStorage.setItem("logout-event", Date.now());
            }

            // escucha en todas las pestañas abiertas
            window.addEventListener("storage", function (event) {
                if (event.key === "logout-event") {
                    // redirige al login si alguien hizo logout en otra pestaña
                    window.location.href = "/login";
                }
            });
    


