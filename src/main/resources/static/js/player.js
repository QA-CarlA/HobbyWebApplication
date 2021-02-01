
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
    let message = "";
    let boolName = true;
    let boolIGN = true;
    let boolTeamID = true;
    if(playerNameValue == "")
    {
        boolName = false;
        message = "Please enter a valid player name";
        output(createDisplay, message);
    }
    if(playerIGNValue == "")
    {
        boolIGN = false;
        message = "Please enter a valid IGN";
        output(createDisplay, message);
    }
    if(teamIDValue <= 0)
    {
        boolTeamID = false;
        message = "Please enter a valid team ID";
        output(createDisplay, message);
    }
    if(boolName == true && boolIGN == true && boolTeamID == true)
    {
        fetch(`http://localhost:8080/player/create` ,
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
}


const viewPlayer = () =>
{
    viewPlayerDisplay.innerHTML = "";
    const viewPlayerIDValue = viewPlayerID.value;
    let message = "";
    fetch(`http://localhost:8080/player/read/${viewPlayerIDValue}`)
    .then(response => response.json())
    .then(json =>
        {
            if (json.playerName != undefined)
            {
                message = `Player ID: ${json.playerID}, Player Name: ${json.playerName}, Player IGN: ${json.playerIGN}`;
                output(viewPlayerDisplay, message);
            }
            else
            {
                message = "Player does not exist";
                output(viewPlayerDisplay, message);
            }
        }).catch(err => console.error("Something went wrong: " + err))
}

const viewAllPlayer = () =>
{
    viewAllDisplay.innerHTML = "";
    fetch(`http://localhost:8080/player/readAll`)
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
    let message = "";
    let boolID = true;
    let boolName = true;
    let boolIGN = true;
    let boolTeamID = true;
    if(updateIDValue <= 0)
    {
        boolID = false;
        message = "Please enter a valid player ID";
        output(updateDisplay, message);
    }
    if(updateNameValue == "")
    {
        boolName = false;
        message = "Please enter a valid player name";
        output(updateDisplay, message);
    }
    if(updateIGNValue == "")
    {
        boolIGN = false;
        message = "Please enter a valid IGN";
        output(updateDisplay, message);
    }
    if(updateTeamIDValue <= 0)
    {
        boolTeamID = false;
        message = "Please enter a valid team ID";
        output(updateDisplay, message);
    }
    let update = false;

    if(boolID == true && boolName == true && boolIGN == true && boolTeamID == true)
    {
        fetch(`http://localhost:8080/player/update/${updateIDValue}` ,
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
                    if(update == true)
                    {
                        message = 'Player has been successfully updated!';
                    }
                    else
                    {
                        message = 'Player Update Failed, Please enter a valid team ID';
                    }
                    output(updateDisplay, message);
                }).catch(err => console.error(err))
    }
}

const deletePlayer = () =>
{
    deleteDisplay.innerHTML = "";
    const deleteIDValue = deleteID.value;
    let p = document.createElement("p");
    let display = undefined;
    fetch(`http://localhost:8080/player/delete/${deleteIDValue}` ,
    {
        method: 'DELETE'
    })
    .then(response => 
        {
            if(response.status != 204)
            {
                display = "Invalid ID";
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