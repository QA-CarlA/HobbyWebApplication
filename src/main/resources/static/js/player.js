
//FORMS
//Create
const playerName = document.querySelector("#playerName");
const playerIGN = document.querySelector("#playerIGN");
const teamID = document.querySelector("#teamID");
//Read
const viewPlayerID = document.querySelector("#viewPlayerID");
//Update
const updateID = document.querySelector("#updateID");
const updateName = document.querySelector("#updateName");
const updateIGN = document.querySelector("#updateIGN");
const updateTeamID = document.querySelector("#updateTeamID");
//Delete
const deleteID = document.querySelector("#deleteID");

//DISPLAY
const createDisplay = document.querySelector("#createDisplay");
const viewAllDisplay = document.querySelector("#viewAllDisplay");
const viewPlayerDisplay = document.querySelector("#viewPlayerDisplay");
const updateDisplay = document.querySelector("#updateDisplay");
const deleteDisplay = document.querySelector("#deleteDisplay");


const createPlayer = () =>
{
    createDisplay.innerHTML = "";
    const playerNameValue = playerName.value;
    const playerIGNValue = playerIGN.value;
    const teamIDValue = teamID.value;
    if(playerNameValue != "" || playerIGNValue != "" || teamIDValue > 0)
    {
        fetch(`http://localhost:8081/player/create` ,
        {
            method: 'POST',
            body: JSON.stringify({
                "playerName" : playerNameValue,
                "playerIGN" : playerIGNValue,
                "team" :
                {
                    "teamID" : teamIDValue
                }
            }),
            headers: 
            {
                'Content-Type': "application/json"
            }
        }).then((response) =>
        {
            if(response.status == 500)
            {
                let fail = `Failed to create player, please enter a valid team ID`;
                output(createDisplay, fail);
            }
            else if(response.ok)
            {
                response.json().then(json => {
                    let success = `${json.playerIGN} has been added!`;
                    output(createDisplay, success);
                })
            }

        });
    }
    else
    {
        let failure = `Please make sure every fields have been entered`;
        output(createDisplay, failure);
        return output;
    }
}


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
    viewAllDisplay.innerHTML = "";
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

const updatePlayer = () =>
{
    updateDisplay.innerHTML = "";
    const updateIDValue = updateID.value;
    const updateNameValue = updateName.value;
    const updateIGNValue = updateIGN.value;
    const updateTeamIDValue = updateTeamID.value;
    let update = false;
    fetch(`http://localhost:8081/player/update/${updateIDValue}` ,
        {
            method: 'PUT',
            body: JSON.stringify({
                "playerName" : updateNameValue,
                "playerIGN" : updateIGNValue,
                "team" :
                {
                    "teamID" : updateTeamIDValue
                }
            }),
            headers: 
            {
                'Content-Type': "application/json"
            }
        }).then(response => 
            {
                if(response.status === 202)
                {
                    update = true;
                    response.json();
                }
            }).then(json => 
                {
                    let details = null;
                    if(update == true)
                    {
                        details = 'Update Successful!';
                    }
                    else
                    {
                        details = 'Update Failed!';
                    }
                    output(updateDisplay, details);
                }).catch(err => console.error(err))
}

const deletePlayer = () =>
{
    deleteDisplay.innerHTML = "";
    const deleteIDValue = deleteID.value;
    let p = document.createElement("p");
    let display = undefined;
    fetch(`http://localhost:8081/player/delete/${deleteIDValue}` ,
    {
        method: 'DELETE'
    })
    .then(response => 
        {
            if(response.status != 204)
            {
                display = "Player could not be deleted";
            }
            else
            {
                display = "Player Deleted!";
            }
            output(deleteDisplay, display);
        }).catch(err => console.error("Something went wrong: " + err))
}


const output = (element, text) => 
{
    let p = document.createElement("p");
    let details = document.createTextNode(text);
    p.appendChild(details);
    element.appendChild(p);
}

//Button event listeners
createBtn.addEventListener('click', createPlayer);
viewPlayerBtn.addEventListener('click', viewPlayer);
viewAllBtn.addEventListener('click', viewAllPlayer);
updateBtn.addEventListener('click', updatePlayer);
deleteBtn.addEventListener('click', deletePlayer);