
//--------------------------------------------------------------------------------//
const allSideMenu = document.querySelectorAll('#sidebar .side-menu.top li a');

allSideMenu.forEach(item => {
    const li = item.parentElement;

    item.addEventListener('click', function () {
        allSideMenu.forEach(i => {
            i.parentElement.classList.remove('active');
        })
        li.classList.add('active');
    })
});




// TOGGLE SIDEBAR
const menuBar = document.querySelector('#content nav .bx.bx-menu');
const sidebar = document.getElementById('sidebar');

menuBar.addEventListener('click', function () {
    sidebar.classList.toggle('hide');
})







const searchButton = document.querySelector('#content nav form .form-input button');
const searchButtonIcon = document.querySelector('#content nav form .form-input button .bx');
const searchForm = document.querySelector('#content nav form');

searchButton.addEventListener('click', function (e) {
    if (window.innerWidth < 576) {
        e.preventDefault();
        searchForm.classList.toggle('show');
        if (searchForm.classList.contains('show')) {
            searchButtonIcon.classList.replace('bx-search', 'bx-x');
        } else {
            searchButtonIcon.classList.replace('bx-x', 'bx-search');
        }
    }
})





if (window.innerWidth < 768) {
    sidebar.classList.add('hide');
} else if (window.innerWidth > 576) {
    searchButtonIcon.classList.replace('bx-x', 'bx-search');
    searchForm.classList.remove('show');
}


window.addEventListener('resize', function () {
    if (this.innerWidth > 576) {
        searchButtonIcon.classList.replace('bx-x', 'bx-search');
        searchForm.classList.remove('show');
    }
})



const switchMode = document.getElementById('switch-mode');

switchMode.addEventListener('change', function () {
    if (this.checked) {
        document.body.classList.add('dark');
    } else {
        document.body.classList.remove('dark');
    }
})


//Left bar
document.addEventListener("DOMContentLoaded", function () {
    const currentPage = window.location.pathname;
    const pageToMenuItem = {
        "Scr_EditProfile_EditProfileCustomer.html": "Edit Profile",
        "Scr_EditProfile_ChangePass.html": "Change Pass",
        "Scr_EditProfile_EditProfileDriver.html": "Edit Profile",
        "Scr_EditProfile_EditProfileStaff.html": "Edit Profile",
        "Scr_EditProfile_EditProfileManager.html": "Edit Profile",
        "": "Manage Payment"
    };

    const menuItems = document.querySelectorAll("#edit-profile ul li a");
    menuItems.forEach(item => {
        if (item.getAttribute('href') === currentPage.split('/').pop()) {
            item.parentElement.style.backgroundColor = "#d3faff";
            item.parentElement.style.borderRadius = "12px";
            item.parentElement.style.padding = "5px";
        }
    });
});

document.addEventListener('DOMContentLoaded', function () {
    document.getElementById('image-upload').addEventListener('change', function () {
        document.getElementById('apply-button').style.display = 'block';
    });
});

function showImage(event) {
    const input = event.target;
    const file = input.files[0];
    if (file) {
        const reader = new FileReader();
        reader.onload = function (e) {
            const img = document.getElementById('pic');
            img.src = e.target.result;
            img.hidden = false;
        };
        reader.readAsDataURL(file);
    }
}

