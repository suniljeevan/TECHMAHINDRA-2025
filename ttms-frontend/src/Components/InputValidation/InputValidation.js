export const isValidEmail = (email) => {
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
    return emailRegex.test(email);
  };
  
  export const isValidContact = (contact) => {
    const contactRegex = /^\d{10}$/;
    return contactRegex.test(contact);
  };
  