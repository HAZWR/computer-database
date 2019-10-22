// Wait for the DOM to be ready
$(function() {
  // Initialize form validation on the registration form.
  // It has the name attribute "registration"
  $("form[name='formAddComputer']").validate({
    // Specify validation rules
    rules: {
      // The key name on the left side is the name attribute
      // of an input field. Validation rules are defined
      // on the right side
    	nom: "required",
    	introducedDate: "required",
    	discontinuedDate: "required",
    	companyEntity: "required"
    },
    // Specify validation error messages
    messages: {
      nom: "Please enter the computer name",
      introducedDate: "Please enter the introducedDate",
      discontinuedDate: "Please enter a valid email address",
      companyEntity: "Please choose a company name"
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });
});