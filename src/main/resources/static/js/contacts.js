const baseUrl = "http://localhost:8080";
const viewContactModal = document.getElementById("view_contact_modal");
console.log("in contactact js");
// options with default values
const options = {
    placement: 'bottom-right',
    backdrop: 'dynamic',
    backdropClasses:
        'bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40',
    closable: true,
    onHide: () => {
        console.log('modal is hidden');
    },
    onShow: () => {
        console.log('modal is shown');
    },
    onToggle: () => {
        console.log('modal has been toggled');
    },
};

// instance options object
const instanceOptions = {
  id: 'view_contact_modal',
  override: true
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

function openContactModal() {
    contactModal.show();
}

function closeContactModal() {
    contactModal.hide();
}

async function loadContactData(id) {
    // function call to load contact data
    // const data = await fetch(`http://localhost:8080/api/contacts/${id}`).then(async (response) => {
    //     let data = await response.json();
    //     console.log(data);
    //     return data;
    // }).catch((error) => {
    //     console.log(error);
    // });

    try {
        const data = await (await fetch(`${baseUrl}/api/contacts/${id}`)).json();
        console.log(data);
        document.querySelector('#contact_name').innerHTML = data.name;
        document.querySelector('#contact_email').innerHTML = data.email;
        document.querySelector('#contact_image').src = '/images/' + data.picture;
        document.querySelector('#contact_address').innerHTML = data.address;
        document.querySelector('#contact_phone').innerHTML = data.phoneNumber;
        document.querySelector('#contact_about').innerHTML = data.description;
        const contactFavorite = document.querySelector("#contact_favorite");
        if(data.favorite){
            contactFavorite.innerHTML = '<i class="fas fa-star text-yellow-400"></i> Favorite Contact';
        }
        else {
            contactFavorite.innerHTML = "Not a Favorite Contact";
        }
        document.querySelector('#contact_website').href = data.websiteLink;
        document.querySelector('#contact_website').innerHTML = data.websiteLink;
        document.querySelector('#contact_linkedin').href = data.linkedInLink;
        document.querySelector('#contact_linkedin').innerHTML = data.linkedInLink;

        openContactModal();
    } catch (error) {
        console.log("Error : ", error);
    }

    // console.log(data.name);
}

// delete contact
async function deleteContact(id){
    // sweet alert 
    Swal.fire({
    title: "Are you sure?",
    text: "You won't be able to revert this!",
    icon: "warning",
    showCancelButton: true,
    // confirmButtonColor: "#3085d6",
    // cancelButtonColor: "#d33",
    confirmButtonText: "Yes, delete it!",

    // fix button visibility problem (button not showing until hover)
    customClass: {
            actions: "flex gap-3",   // THIS controls spacing between buttons
            confirmButton: "opacity-100 text-white bg-blue-600 px-4 py-2 rounded",
            cancelButton: "opacity-100 text-white bg-red-600 px-4 py-2 rounded"
        },
    buttonsStyling: false

    }).then(async (result) => {
    if (result.isConfirmed){

        try {
            const response = await fetch(`${baseUrl}/user/contacts/delete/${id}`);

            if (response.ok) {
                await Swal.fire({
                    title: "Deleted!",
                    text: "Contact deleted successfully.",
                    icon: "success",

                    // fix button visibility problem
                    buttonsStyling: false,
                    customClass: {
                        confirmButton: "opacity-100 bg-purple-600 text-white px-4 py-2 rounded"
                    }
                });

                // remove row OR reload
                location.reload();
            } else {
                Swal.fire("Error", "Failed to delete contact", "error");
            }

        } catch (error) {
            Swal.fire("Error", "Something went wrong", "error");
        }

        // await Swal.fire({
        // title: "Deleted!",
        // text: "Your file has been deleted.",
        // icon: "success"
        // });

        // // redirect to delete url
        // const url = `${baseUrl}/user/contacts/delete/${id}`;
        // window.location.replace(url);

    } 
    });

}