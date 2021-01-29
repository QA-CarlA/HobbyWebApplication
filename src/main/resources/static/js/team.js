

//Create Form
const teamName = document.querySelector("#teamName");
//Read Form
const viewTeamID = document.querySelector("#viewTeamID");
//Update Form
const updateID = document.querySelector("#updateID");
const updateName = document.querySelector("#updateName");
//Delete Form
const deleteID = document.querySelector("#deleteID");
//Display
const createDisplay = document.querySelector("#createDisplay");
const viewTeamDisplay = document.querySelector("#viewTeamDisplay");
const ViewAllDisplay = document.querySelector("#viewAllDisplay");
const updateDisplay = document.querySelector("#updateDisplay");
const deleteDisplay = document.querySelector("#deleteDisplay");

const createTeam = () =>
{
    createDisplay.innerHTML = "";
    const teamNameValue = teamName.value;
    let message = "";
    if (teamNameValue == "")
    {
        message = "Team creation failed! Please enter a valid team name!";
        output(createDisplay, message);
    }
    else
    {
        fetch(`http://localhost:8081/team/create` ,
        {
            method: 'POST',
            body: JSON.stringify({
                "teamName" : teamNameValue
            }),
            headers:
            {
                'Content-Type': "application/json"
            }
        }).then(response => response.json())
        .then(json => 
            {
                message = `${json.teamName} has been created!`;
                output(createDisplay, message);
            }).catch(err => console.error("Developer could not be created: " + err))
    }
}

const viewTeam = () =>
{
    viewTeamDisplay.innerHTML = "";
    const viewTeamIDValue = viewTeamID.value;
    let message = "";
    fetch(`http://localhost:8081/team/read/${viewTeamIDValue}`)
    .then(response => response.json())
    .then(json => {
        if(json.teamName != undefined)
        {
            message = `Team Name: ${json.teamName}`;
            output(viewTeamDisplay, message);
            for(let i = 0; i < json.listPlayer.length; i++)
            {
                message = `${json.listPlayer[i].playerName} '${json.listPlayer[i].playerIGN}'`;
                output(viewTeamDisplay, message);
            }
        }
        else
        {
            message = "Team does not exist";
            output(viewTeamDisplay, message);
        }
    })

}

const viewAllTeam = () =>
{
    viewAllDisplay.innerHTML = "";
    let message = "";
    fetch(`http://localhost:8081/team/readAll`)
    .then(response => response.json())
    .then(json => {
        for(let i = 0; i < json.length; i++)
        {
            message = `Team ID: ${json[i].teamID}, Team Name: ${json[i].teamName}`;
            output(viewAllDisplay, message);
            for(let j = 0; j < json[i].listPlayer.length; j++)
            {
                message = `${json[i].listPlayer[j].playerName} '${json[i].listPlayer[j].playerIGN}'`;
                output(viewAllDisplay, message);
            }
        }
    });
}

const updateTeam = () =>
{
    updateDisplay.innerHTML = "";
    const updateIDValue = updateID.value;
    const updateNameValue = updateName.value;
    let message = "";
    let boolID = true;
    let boolName = true;
    if(updateIDValue <= 0)
    {
        boolID = false;
        message = "Please enter a valid team ID";
        output(updateDisplay, message);
    }
    if(updateNameValue == "")
    {
        boolName = false;
        message = "Please enter a valid team name";
        output(updateDisplay, message);
    }
    if (boolID == true && boolName == true)
    {
        fetch(`http://localhost:8081/team/update/${updateIDValue}` ,
        {
            method: 'PUT',
            body: JSON.stringify({
                "teamName" : updateNameValue
            }),
            headers:
            {
                'Content-Type': "application/json"
            }
        })
        .then((response) => {
            if (response.status == 202)
            {
                message = "Team has successfully been updated!";
                output(updateDisplay, message);
            }
            else
            {
                message = "Team update failed, Please enter a valid team ID";
                output(updateDisplay, message);
            }
        });
    }
}

const deleteTeam = () =>
{
    deleteDisplay.innerHTML = "";
    const deleteIDValue = deleteID.value;
    let message = "";
    fetch(`http://localhost:8081/team/delete/${deleteIDValue}` ,
    {
        method: 'DELETE'
    })
    .then((response) => {
        if (response.ok)
        {
            message = "Team has been deleted!";
            output(deleteDisplay, message);
        }
        else
        {
            message = "Invalid ID";
            output(deleteDisplay, message);
        }
    });
}


const output = (element, text) => 
{
    let p = document.createElement("p");
    let details = document.createTextNode(text);
    p.appendChild(details);
    element.appendChild(p);
}

//BUTTONS
createBtn.addEventListener('click', createTeam);
viewTeamBtn.addEventListener('click', viewTeam);
viewAllBtn.addEventListener('click', viewAllTeam);
updateBtn.addEventListener('click', updateTeam);
deleteBtn.addEventListener('click', deleteTeam);