package bg.fon.huntingassociation.domain.dtos;

public class AddressDto {
    private String address;

    public AddressDto(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
