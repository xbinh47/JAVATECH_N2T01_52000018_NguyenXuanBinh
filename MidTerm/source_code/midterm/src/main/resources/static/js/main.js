$(document).ready(function () {
  const account_id = document.getElementById("account_id").value
  let cart = []
  function renderProducts(products) {
    document.getElementById("products").innerHTML = ""
    for (let items of products) {

      let card = document.createElement("div")
      card.classList.add("card")
      card.addEventListener("click", function () {
        addToCart(items.id)
      })

      let imgContainer = document.createElement("div")
      imgContainer.classList.add("image-container")

      let image = document.createElement("img")
      image.setAttribute("src", items.image)
      imgContainer.appendChild(image)
      card.appendChild(imgContainer)

      let container = document.createElement("div")
      container.classList.add("container")

      let name = document.createElement("h5");
      name.classList.add("product-name");
      name.innerText = items.name.toUpperCase();
      container.appendChild(name);

      let price = document.createElement("h6");
      price.innerHTML = "<b>Price:</b> " + items.price;
      container.appendChild(price);

      card.appendChild(container);
      document.getElementById("products").appendChild(card);


    }
  }
  function searchRenderProduct(data) {
    $('#search-detail-list').empty()
    if (data.length === 0) {
      let ul = document.getElementById("search-detail-list")
      ul.style.display = "none"
      return
    }
    let list = ""
    let ul = document.getElementById("search-detail-list")
    ul.style.display = "block"
    data?.forEach(item => {
      list += `<li><a class="dropdown-item" href="/${item.id}">${item.name}</a></li>`
    })
    ul.innerHTML = list
  }

  $('#search-input').on('keyup', function (event) {
    let value = $(this).val().toLowerCase();
    if (value.length === 0) {
      let ul = document.getElementById("search-detail-list")
      ul.style.display = "none"
      return
    }

    if(event.keycode === 13) {
        $('#search-btn').click()
    }
    fetch(`/api/getProductsByName?name=${value}`)
      .then(response => response.json())
      .then(data => {
        searchRenderProduct(data.data)
      })
  })
  $('#search-input').focusout(function () {
    if($('#search-detail-list').is(':hover')) {
        return
    }
    let ul = document.getElementById("search-detail-list")
    ul.style.display = "none"
  })

  document.getElementById("btn-filter-submit").addEventListener("click", function () {
    let categorySelect = document.getElementById("category-select").value;
    let priceSelect = document.getElementById("price-select").value;
    let sortSelect = document.getElementById("sort-select").value;
    let brandSelect = document.getElementById("brand-select").value;
    let price1 = priceSelect.split("-")[0]
    let price2 = priceSelect.split("-")[1]
    let name = document.getElementById("search-name").innerText
    let url = `/api/getProducts?category_id=${categorySelect}&price1=${price1}&price2=${price2}&sort_type=${sortSelect}&brand=${brandSelect}&name=${name}`
    fetch(url)
      .then(response => response.json())
      .then(data => {
        renderProducts(data.data)
      })
  })

  $('.add-to-cart-btn').click(function () {
    let id = $(this).attr('product_id')
    window.location.href = `/${id}`
  })

  function addToCart(id) {
    window.location.href = `/${id}`
  }

  $('#search-btn').click(function () {
    let value = $('#search-input').val().toLowerCase();
    let categorySelect = document.getElementById("category-select").value;
    let priceSelect = document.getElementById("price-select").value;
    let sortSelect = document.getElementById("sort-select").value;
    let brandSelect = document.getElementById("brand-select").value;
    let price1 = priceSelect.split("-")[0]
    let price2 = priceSelect.split("-")[1]
    document.getElementById("search-name").innerText = value
    fetch(`/api/getProducts?name=${value}&category_id=${categorySelect}&price1=${price1}&price2=${price2}&sort_type=${sortSelect}&brand=${brandSelect}`)
      .then(response => response.json())
      .then(data => {
        renderProducts(data.data)
      })
  })

})