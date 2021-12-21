const token = window.localStorage.getItem("token");
const accountId = window.localStorage.getItem("accountId");
const scooterId = window.localStorage.getItem("scooterId");
const scooterContent = document.getElementById("scooters-content");

if (token) {
    if(scooterId) scooterInfo(scooterId);
    else scooterList();
} else {
    requestAuth();
}

function requestAuth() {
    scooterContent.innerHTML = "";

    const div = document.createElement("div");

    div.appendChild((() => {
        const header = document.createElement('h1')
        header.innerHTML = "Please sign in or create account firstly";

        return header;
    })())

    scooterContent.appendChild(div);
}

function scooterList() {
    scooterContent.innerHTML = "";

    fetch('/api/v1/scooter/list', {
        headers: {
            'Accept-Type': 'application/json',
            'Authorization': `Bearer ${token}`
        }
    }).then(async (response) => {
        if(response.ok) {
            const data = await response.json() || [];

            if(data.length) {
                data.forEach(scooter => scooterContent.appendChild(scooterComponent(scooter)))
            }
        } else if (response.status === 403) {
            requestAuth()
        }
    })
}

async function scooterInfo(id) {
    scooterContent.innerHTML = "";

    const getByIdResponse = await fetch(`/api/v1/scooter/get?id=${id}`, {
        headers: {
            'Accept-Type': 'application/json',
            "Authorization": `Bearer ${token}`
        }
    })

    if(!getByIdResponse.ok) {
        scooterList();
        return;
    }

    const scooter = await getByIdResponse.json();

    // get info about account-scooter by scooter Id;

    const container = document.createElement('div');
    container.appendChild((() => {
        const header = document.createElement('h1');
        header.innerHTML = "Scooter Info";

        return header;
    })());

    container.appendChild((() => {
        const button = document.createElement('button');
        button.innerHTML = "Stop";
        button.onclick = () => {
            window.localStorage.removeItem("scooterId");
            // delete account-scooter (delete)
            scooterList();
        }

        return button;
    })());

    scooterContent.appendChild(container);
}

function scooterComponent(scooter) {
    const container = document.createElement('div');
    container.appendChild((() => {
        const header = document.createElement('h1');
        header.innerHTML = scooter.model;

        return header;
    })());
    container.appendChild((() => {
        const price = document.createElement('p');
        price.innerHTML = scooter.price.toFixed(2);

        return price;
    })());
    container.appendChild((() => {
        const button = document.createElement('button');
        button.innerHTML = 'Take this!';
        button.onclick = () => {
            window.localStorage.setItem("scooterId", scooter.id);
            fetch("/api/v1/scooter/rent", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                },
                body: JSON.stringify({
                    accountId,
                    scooterId: scooter.id
                })
            })
            scooterInfo(scooter.id);
        }

        return button;
    })());

    return container;
}
