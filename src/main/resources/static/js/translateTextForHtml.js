document.addEventListener("DOMContentLoaded", function() {
    const descriptions = document.querySelectorAll('.description, #descriptionText, .questionTitle');
    descriptions.forEach(function(element) {
        element.innerHTML = element.textContent.replace(/\n/g, '<br/>');
    });
});