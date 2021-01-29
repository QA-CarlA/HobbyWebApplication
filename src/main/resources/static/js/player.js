
//Forms
const viewPlayerID = document.querySelector("#viewPlayerID");

//Display
const viewAllDisplay = document.querySelector("#viewAllDisplay");
const viewPlayerDisplay = document.querySelector("#viewPlayerDisplay");

const viewPlayer = () =>
{
    viewPlayerDisplay.innerHTML = "";
    const viewPlayerIDValue = viewPlayerID.value;
    fetch(`http://localhost:8081/player/read/${viewPlayerIDValue}`)
    .then(response => response.json())
    .then(json =>
        {
            console.log(json);
            let details = `Player ID: ${json.playerID}, Player Name: ${json.playerName}, Player IGN: ${json.playerIGN}`;
            output(viewPlayerDisplay, details);
        }).catch(err => console.error("Something went wrong: " + err))
}

const viewAllPlayer = () =>
{
    viewAllDisplay.innerHTML = " ";
    fetch(`http://localhost:8081/player/readAll`)
    .then(response => response.json())
    .then(json => {
        console.log(json);
        for(let i = 0; i< json.length; i++)
        {
            let details = `Player ID: ${json[i].playerID}, Player Name: ${json[i].playerName}, 
            Player IGN: ${json[i].playerIGN}`;
            output(viewAllDisplay, details);
        }
    }).catch(err => console.error("Something went wrong: " + err))
}

const output = (element, text) => 
{
    let p = document.createElement("p");
    let details = document.createTextNode(text);
    p.appendChild(details);
    element.appendChild(p);
}

viewPlayerBtn.addEventListener('click', viewPlayer);
viewAllBtn.addEventListener('click', viewAllPlayer);