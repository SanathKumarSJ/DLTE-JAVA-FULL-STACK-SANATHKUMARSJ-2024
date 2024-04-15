
const payeeRecords = [
  { senderAccount: '12345', payeeName: 'Sanathkumar', receiverAccount: '56789', payeeId: 100 },
  { senderAccount: '12345', payeeName: 'Arundathi', receiverAccount: '96325', payeeId: 101 },
  { senderAccount: '12346', payeeName: 'Eeksha', receiverAccount: '921501', payeeId: 102 }
];

// Store payee records in local storage
//convert the array into a JSON string before storing it.
localStorage.setItem('payeeRecords', JSON.stringify(payeeRecords));

//DOMContentLoaded event, which fires when the initial HTML document has been completely loaded and parsed,
// without waiting for stylesheets, images, and subframes to finish loading.
window.addEventListener('DOMContentLoaded', () => {
  const currentPage = window.location.pathname.split('/').pop();

  if (currentPage === 'page2.html') {
    displayPayeeCards();
  } else if (currentPage === 'page3.html') {
    displayPayeeRecords();
  }
});

function fetchPayeeNames() {
  const senderAccount = document.getElementById('senderAccount').value;
  const payeeRecords = JSON.parse(localStorage.getItem('payeeRecords'));
  const payeeNames = payeeRecords
    .filter(record => record.senderAccount === senderAccount)
    .map(record => record.payeeName);

  if (payeeNames.length === 0) {
    alert("No account found");
    return;
  }

  localStorage.setItem('payeeNames', JSON.stringify(payeeNames));
  window.location.href = 'page2.html';
}


function displayPayeeCards() {
  const payeeNames = JSON.parse(localStorage.getItem('payeeNames'));
  const payeeCards = document.getElementById('payeeCards');

  
// encodeURIComponent() is used to encode the value of payeeName
        //URL the hyperlink will navigate
  payeeNames.forEach(payeeName => {
    const card = document.createElement('div');
    card.className = 'card col-sm-4 m-2';
    card.innerHTML = `
      <img src="files/img8.jpg" class="card-img-top" alt="Profile Icon">
      <div class="card-body">
        <h5 class="card-title">${payeeName}</h5>

        
        <a href="page3.html?payeeName=${encodeURIComponent(payeeName)}" class="btn btn-primary">View Records</a>
      </div>
    `;
    // add a new child node(card) to an existing parent node in the HTML Document Object Model 
    payeeCards.appendChild(card);
  });
}

function displayPayeeRecords() {
  const payeeRecords = JSON.parse(localStorage.getItem('payeeRecords'));
  const payeeRecordsTable = document.getElementById('payeeRecords');
  const urlParams = new URLSearchParams(window.location.search);
  const payeeName = decodeURIComponent(urlParams.get('payeeName'));

  const filteredRecords = payeeRecords.filter(record => record.payeeName === payeeName);

  filteredRecords.forEach(record => {
    const row = document.createElement('tr');
    row.innerHTML = `
      <td>${record.payeeId}</td>
      <td>${record.senderAccount}</td>
      <td>${record.receiverAccount}</td>
      <td>${record.payeeName}</td>
    `;
    payeeRecordsTable.appendChild(row);
  });
}









// // // // script.js

// // // // Function to handle form submission on the first page
// // // document.getElementById('senderForm').addEventListener('submit', function(event) {
// // //     event.preventDefault();
    
// // //     // Get the sender's account number from the input field
// // //     var senderAccount = document.getElementById('senderAccount').value;
    
// // //     // Store the sender's account number in local storage
// // //     localStorage.setItem('senderAccount', senderAccount);
    
// // //     // Redirect to the second page to display payee names
// // //     window.location.href = 'payeeNames.html';
// // // });

// // // // Function to retrieve payee names and display them in cards on the second page
// // // document.addEventListener('DOMContentLoaded', function() {
// // //     var senderAccount = localStorage.getItem('senderAccount');
// // //     if (senderAccount) {
// // //         // Here you would fetch payee names based on the sender's account number from your data source
// // //         var payeeNames = ['Payee 1', 'Payee 2', 'Payee 3']; // Dummy data, replace with actual data
        
// // //         var payeeCardsContainer = document.getElementById('payeeCards');
// // //         payeeNames.forEach(function(payeeName) {
// // //             var card = document.createElement('div');
// // //             card.classList.add('card');
// // //             card.innerHTML = `
// // //                 <div class="card-body">
// // //                     <h5 class="card-title">${payeeName}</h5>
// // //                 </div>
// // //             `;
// // //             payeeCardsContainer.appendChild(card);
            
// // //             // Add click event listener to each card to redirect to the third page when clicked
// // //             card.addEventListener('click', function() {
// // //                 localStorage.setItem('selectedPayee', payeeName);
// // //                 window.location.href = 'payeeRecords.html';
// // //             });
// // //         });
// // //     }
// // // });

// // // // Function to retrieve and display payee records for the selected payee on the third page
// // // document.addEventListener('DOMContentLoaded', function() {
// // //     var selectedPayee = localStorage.getItem('selectedPayee');
// // //     if (selectedPayee) {
// // //         // Here you would fetch payee records for the selected payee from your data source
// // //         var payeeRecords = [
// // //             { id: 1, amount: 100, date: '2024-04-10' },
// // //             { id: 2, amount: 150, date: '2024-04-12' },
// // //             // Add more payee records as needed
// // //         ]; // Dummy data, replace with actual data
        
// // //         var payeeTable = document.getElementById('payeeTable');
// // //         var tableHtml = `
// // //             <table class="table">
// // //                 <thead>
// // //                     <tr>
// // //                         <th>ID</th>
// // //                         <th>Amount</th>
// // //                         <th>Date</th>
// // //                     </tr>
// // //                 </thead>
// // //                 <tbody>
// // //         `;
// // //         payeeRecords.forEach(function(record) {
// // //             tableHtml += `
// // //                 <tr>
// // //                     <td>${record.id}</td>
// // //                     <td>${record.amount}</td>
// // //                     <td>${record.date}</td>
// // //                 </tr>
// // //             `;
// // //         });
// // //         tableHtml += `
// // //                 </tbody>
// // //             </table>
// // //         `;
// // //         payeeTable.innerHTML = tableHtml;
// // //     }
// // // });
