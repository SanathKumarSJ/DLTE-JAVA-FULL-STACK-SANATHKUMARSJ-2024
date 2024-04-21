
//payee records
const payeeList = [
    {
      payeeid: 501,senderAccountNumber: '12345',payeeAccountNumber: '96325',payeeName: 'Sanath'
    },
    {
      payeeid: 502,senderAccountNumber: '12345',payeeAccountNumber: '984564156',payeeName: 'Mahesh'
    },
    {
      payeeid: 503,senderAccountNumber: '12345',payeeAccountNumber: '156153',payeeName: 'Tejas'
    },
    {
      payeeid: 504,senderAccountNumber: '12345',payeeAccountNumber: '235452135',payeeName: 'Pramith'
    },
    {
      payeeid: 505,senderAccountNumber: '12345',payeeAccountNumber: '235452177',payeeName: 'Varshini Shetty'
    },
    {
      payeeid: 506,senderAccountNumber: '123456',payeeAccountNumber: '56456654',payeeName: 'Akash'
    }
  ];
  

  //declaring pages and card continer
  const cardContainer = $('#cardContainer');
  let currentPage = 1;
  const cardsPerPage = 2;
  let filteredPayees = [];
  

  //function to crerds based on payee record
  function createCard(payee) {
    const card = $('<div class="card col-md-4  m-2"></div>');
    card.html(`
        <div class="card-body">
        <h5 class="card-title">${payee.payeeName}</h5>
        <p class="card-text">Payee ID: ${payee.payeeid}</p>
        <p class="card-text">Sender Account Number: ${payee.senderAccountNumber}</p>
        <p class="card-text">Payee Account Number: ${payee.payeeAccountNumber}</p>
        <div class="text-center">
        <button class="btn btn-primary view-btn">View</button>
        </div>
        </div>
    `);
  
    //function to display the payee records
    card.find('.view-btn').on('click', () => {
      alert(JSON.stringify(payee))
    });
  
    return card;
  }
  
  //filter the payee based on payee name
  function filterPayees(query) {
    return payeeList.filter(payee => payee.payeeName.toLowerCase().includes(query.toLowerCase()));
  }
  
  //search box for entering payee name and for filtering
  $('#searchBox').on('input', () => {
    const query = $('#searchBox').val();
    filteredPayees = filterPayees(query);
    currentPage = 1;
    displayPayees();
    updatePagination();
  });
  
  // display the payee names in the particular pages
  function displayPayees() {
    const startIndex = (currentPage - 1) * cardsPerPage;
    const endIndex = startIndex + cardsPerPage;
  
    cardContainer.empty();
    filteredPayees.slice(startIndex, endIndex).forEach(payee => {
        cardContainer.append(createCard(payee));
    });
  }
  

  //pagination updation 
  function updatePagination() {
    const totalPages = Math.ceil(filteredPayees.length / cardsPerPage);
    const pagination = $('#pagination');
    pagination.empty();
  
    for (let i = 1; i <= totalPages; i++) {
        const button = $(`<button class="btn btn-outline-primary col-2 m-2 mr-3">${i}</button>`);
  
        if (i === currentPage) {
            button.addClass('active');
        }
  
        button.on('click', () => {
            currentPage = i;
            displayPayees();
            updatePagination();
        });
  
        pagination.append(button);
    }
  }
  
  $('#accountNumber').on('input', () => {
    const accountNumber = $('#accountNumber').val();
    filteredPayees = payeeList.filter(payee => payee.senderAccountNumber === accountNumber);
    currentPage = 1;
    displayPayees();
    updatePagination();
  });
  
  
  displayPayees();
  updatePagination();