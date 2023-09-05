const chatContent = document.getElementById('chatContent');
const chatInputs = document.getElementById('chatInputs');
let fdetails = {};


function appendUserMessage(message) {
    const userMsg = document.createElement('div');
    userMsg.className = 'chat-message user-message';
    userMsg.textContent = message;
    chatContent.appendChild(userMsg);
}

function appendBotMessage(message) {
    const botMsg = document.createElement('div');
    botMsg.className = 'chat-message bot-message';
    botMsg.textContent = message;
    chatContent.appendChild(botMsg);
}

function askForApplicationID() {
    chatInputs.innerHTML = `
        <input type="text" id="applicationId" >
        <button onclick="collectId()">Next</button>
    `;
}
function askForName() {
    chatInputs.innerHTML = `
        <input type="text" id="name" placeholder="Enter your Name">
        <button onclick="collectName()">Next</button>
    `;
}
function collectId() {
    fdetails.applicationId = document.getElementById('applicationId').value;
    appendUserMessage(fdetails.applicationId);
    askForState();
}
function collectName() {
    fdetails.name = document.getElementById('name').value;
    appendUserMessage(fdetails.name);
    appendBotMessage("Enter Your Application Id");
    askForApplicationID();
}

function askForState() {
    chatInputs.innerHTML = `
    <select name="states" id="states">
    <option value="Andhra Pradesh">Andhra Pradesh</option>
    <option value="Arunachal Pradesh">Arunachal Pradesh</option>
    <option value="Assam">Assam</option>
    <option value="Bihar">Bihar</option>
    <option value="Chhattisgarh">Chhattisgarh</option>
    <option value="Goa">Goa</option>
    <option value="Gujarat">Gujarat</option>
    <option value="Haryana">Haryana</option>
    <option value="Himachal Pradesh">Himachal Pradesh</option>
    <option value="Jharkhand">Jharkhand</option>
    <option value="Karnataka">Karnataka</option>
    <option value="Kerala">Kerala</option>
    <option value="Madhya Pradesh">Madhya Pradesh</option>
    <option value="Maharashtra">Maharashtra</option>
    <option value="Manipur">Manipur</option>
    <option value="Meghalaya">Meghalaya</option>
    <option value="Mizoram">Mizoram</option>
    <option value="Nagaland">Nagaland</option>
    <option value="Odisha">Odisha</option>
    <option value="Punjab">Punjab</option>
    <option value="Rajasthan">Rajasthan</option>
    <option value="Sikkim">Sikkim</option>
    <option value="Tamil Nadu">Tamil Nadu</option>
    <option value="Telangana">Telangana</option>
    <option value="Tripura">Tripura</option>
    <option value="Uttar Pradesh">Uttar Pradesh</option>
    <option value="Uttarakhand">Uttarakhand</option>
    <option value="West Bengal">West Bengal</option>
    <!-- Union Territories -->
    <option value="Andaman and Nicobar Islands">Andaman and Nicobar Islands</option>
    <option value="Chandigarh">Chandigarh</option>
    <option value="Dadra and Nagar Haveli and Daman and Diu">Dadra and Nagar Haveli and Daman and Diu</option>
    <option value="Lakshadweep">Lakshadweep</option>
    <option value="Delhi">Delhi</option>
    <option value="Puducherry">Puducherry</option>
    <option value="Ladakh">Ladakh</option>
    <option value="Jammu and Kashmir">Jammu and Kashmir</option>
</select>

        <button onclick="collectState()">Next</button>
    `;
    appendBotMessage("Which state are you from?");
}

function collectState() {
    fdetails.state = document.getElementById('states').value;
    appendUserMessage(fdetails.state);
    askForCity();
}

function askForCity() {
    chatInputs.innerHTML = `
    <label for="district">District:</label>
    <select id="district">
    <option value="Andaman and Nicobar Islands">Andaman and Nicobar Islands</option>
    <option value="Chandigarh">Chandigarh</option>
    <option value="Dadra and Nagar Haveli and Daman and Diu">Dadra and Nagar Haveli and Daman and Diu</option>
    <option value="Lakshadweep">Lakshadweep</option>
    <option value="Delhi">Delhi</option>
    <option value="Puducherry">Puducherry</option>
    <option value="Ladakh">Ladakh</option>
    </select>
    
        <button onclick="collectDistrict()">Next</button>
    `;
    appendBotMessage("Which District are you from?");
}
function askForTaluka() {
    chatInputs.innerHTML = `
    <label for="taluka">Taluka:</label>
    <select id="taluka">
    <option value="Andaman and Nicobar Islands">Andaman and Nicobar Islands</option>
    <option value="Chandigarh">Chandigarh</option>
    <option value="Dadra and Nagar Haveli and Daman and Diu">Dadra and Nagar Haveli and Daman and Diu</option>
    <option value="Lakshadweep">Lakshadweep</option>
    <option value="Delhi">Delhi</option>
    <option value="Puducherry">Puducherry</option>
    <option value="Ladakh">Ladakh</option>
    </select>
    
        <button onclick="collectTaluka()">Next</button>
    `;
    appendBotMessage("Which Taluka are you from?");
}

function collectTaluka() {
    fdetails.taluka = document.getElementById('taluka').value;
    appendUserMessage(fdetails.taluka);
    askForVillage();
}

function collectDistrict() {
    fdetails.district = document.getElementById('district').value;
    appendUserMessage(fdetails.district);
    askForTaluka();
}

function askForVillage() {
    chatInputs.innerHTML = `
        <input type="text" id="village" placeholder="Enter your Village">
        <button onclick="collectVillage()">Next</button>
    `;
    appendBotMessage("Enter your Village");
}

function collectVillage() {
    fdetails.village = document.getElementById('village').value;
    appendUserMessage(fdetails.village);
    askForSurveyNumber();
}
function askForSurveyNumber() {
    chatInputs.innerHTML = `
        <input type="text" id="surveyNumber" >
        <button onclick="collectSurveyNumber()">Next</button>
    `;
    appendBotMessage("Enter your Survey Number");
}

function collectSurveyNumber() {
    fdetails.surveyNumber = document.getElementById('surveyNumber').value;
    appendUserMessage(fdetails.surveyNumber);
    askForCause();
}
function askForCause() {
    chatInputs.innerHTML = `
        <input type="text" id="cause" >
        <button onclick="collectCause()">Next</button>
    `;
    appendBotMessage("Enter your Cause of Loss");
}

function collectCause() {
    fdetails.cause = document.getElementById('cause').value;
    appendUserMessage(fdetails.cause);
    askForDate();
}
function askForDate() {
    chatInputs.innerHTML = `
        <input type="date" id="date" >
        <button onclick="collectDate()">Next</button>
    `;
    appendBotMessage("When did your loss happen");
}

function collectDate() {
    fdetails.date = document.getElementById('date').value;
    appendUserMessage(fdetails.date);
    finish();
}
function finish() {
	let myDiv = document.getElementById("chat-bar-bottom");   
    let btn = document.createElement("button");
   	btn.innerHTML = "Submit";
    btn.type = "submit";
    btn.name = "formBtn";
    myDiv.appendChild(btn);
    btn.addEventListener("click", displayMessage);

    chatInputs.innerHTML = '';
    
    //console.log(userDetails);
    //appendBotMessage("Thank you for submitting your details!");
}
function displayMessage(){
	
	const myObject = {
		applicationId: fdetails.applicationId,
		name: fdetails.name,
		state: fdetails.state,
		district: fdetails.district,
		taluka: fdetails.taluka,
		village: fdetails.village,
		surveyNumber: fdetails.surveyNumber,
		cause: fdetails.cause,
		date: fdetails.date

	};
	sendDataToAngular(myObject);
	document.getElementById("chat-bar-bottom").innerHTML = "Thank you for submitting your details!";
	console.log(fdetails);
}   
function sendDataToAngular(data) {
    var scope = angular.element(document.querySelector('[ng-controller=myCtrl]')).scope();
    scope.receiveDataFromJS(data);
    alert(0);
    scope.$apply(); 
}
            
// Start the chatbot
document.addEventListener("DOMContentLoaded", function() {
   askForName();
});





