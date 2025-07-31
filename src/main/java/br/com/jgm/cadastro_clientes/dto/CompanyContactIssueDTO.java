package br.com.jgm.cadastro_clientes.dto;

public class CompanyContactIssueDTO {

    private final String companyName;
    private final String internalCode;
    private final Long totalWithMissingPhone;
    private final Long totalWithMissingEmail;
    private final Long totalInactive;

    // Construtor EXATO usado na query (ordem e tipos)
    public CompanyContactIssueDTO(String companyName, String internalCode,
                                  Long totalWithMissingPhone, Long totalWithMissingEmail, Long totalInactive) {
        this.companyName = companyName;
        this.internalCode = internalCode;
        this.totalWithMissingPhone = totalWithMissingPhone;
        this.totalWithMissingEmail = totalWithMissingEmail;
        this.totalInactive = totalInactive;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getInternalCode() {
        return internalCode;
    }

    public Long getTotalWithMissingPhone() {
        return totalWithMissingPhone;
    }

    public Long getTotalWithMissingEmail() {
        return totalWithMissingEmail;
    }

    public Long getTotalInactive() {
        return totalInactive;
    }
}
