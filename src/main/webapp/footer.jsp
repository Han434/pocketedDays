<footer class="text-center bgs-b mt-3 p-2 text-white">
  <c:set var="quote" value="${quoteDao.getRandomQuote()}" scope="request" />
  <p><cite class="text-light">${quote.author}: </cite><q>${quote.getQuote()}</q></p>
</footer>