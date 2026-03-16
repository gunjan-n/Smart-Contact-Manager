let currentTheme = getTheme();
const changeThemeButton = document.querySelector("#theme_change_button");

document.addEventListener('DOMContentLoaded', () => {
    changeTheme();
});

// change theme
function changeTheme(){
    // set theme to page
    changePageTheme(currentTheme, currentTheme);

    // set listener to change theme button
    changeThemeButton.addEventListener("click", () => {
        let oldTheme = currentTheme;
        if (currentTheme == "dark") {
            // set theme to light
            currentTheme = 'light';
        } else {
            // set theme to dark
            currentTheme = 'dark';
        }

        changePageTheme(currentTheme, oldTheme);
        
    });
}

// set theme to local storage
function setTheme(theme){
    localStorage.setItem("theme", theme);
}

// get theme from local storage
function getTheme() {
    let theme = localStorage.getItem("theme");
    if (theme) {
        return theme;
    }
    return "Light";
}

// change current page theme
function changePageTheme(theme, oldTheme){

    // update theme in local storage
    setTheme(currentTheme);

     // remove the existing theme
    document.querySelector("html").classList.remove(oldTheme);
    // add the updated theme
    document.querySelector("html").classList.add(theme);

    // change the text of button
    changeThemeButton.querySelector("span").textContent = theme=='light' ? 'Dark' : 'Light';
}
// end of change theme work