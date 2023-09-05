const chatHindiContent = document.getElementById('chatHindiContent');
const chatHindiInputs = document.getElementById('chatHindiInputs');
const bimaDivHindi = document.querySelector('.chatcustomchange[data-type="bimahindi"]');
let farmerdetails = {};
let isMuted = false;

bimaDivHindi.addEventListener('click', function() {
    appendUserMessage('Fasal Bima Yojana');
    askForApplicationIdHindi();
});

function appendUserMessage(message) {
	const userMsg = document.createElement('div');
	userMsg.className = 'chat-message user-message';
	userMsg.textContent = message;
	chatHindiContent.appendChild(userMsg);
	scrollToBottom();
}

function appendBotMessage(message) {
	const botMsg = document.createElement('div');
	botMsg.className = 'chat-message bot-message';
	botMsg.textContent = message;
	chatHindiContent.appendChild(botMsg);
	textToSpeech(message);
	scrollToBottom();
}

function askForApplicationIdHindi(){
	chatHindiInputs.innerHTML = `
        <input type="text" id="applicationId" >
        <span id="applicationIdError" style="color: red;"></span>
        <button id="nextButton" onclick="collectId()">Next</button>
    `;
    const applicationIdInput = document.getElementById('applicationId');
    const nextButton = document.getElementById('nextButton');
    const applicationIdError = document.getElementById('applicationIdError');
    
    appendBotMessage("Please enter your application ID.");

    applicationIdInput.addEventListener('input', function(event) {
        const inputValue = event.target.value;
        if (/^\d+$/.test(inputValue)) {
            applicationIdError.textContent = '';
            nextButton.disabled = false;
        } else {
            applicationIdError.textContent = 'Please enter a valid application ID.';
            nextButton.disabled = true;
        }
    });
}
