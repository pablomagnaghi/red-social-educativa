function validarPasswords(){
	if ($("#pass").val() != $("#reTypePass").val()){
		alert("Las passwords no coinciden entre sí");
		return false;
	}
	return true;
}