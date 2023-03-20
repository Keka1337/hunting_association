package bg.fon.huntingassociation.domain.dtos;

import java.time.LocalDate;

public class VenisonDto {
    private Long id;
    private String name;
    private String latinName;
    private String gender;
    private LocalDate fromDate;
    private LocalDate toDate;
    private int maxNum;

    public VenisonDto(Long id, String name, String latinName, String gender, LocalDate fromDate, LocalDate toDate, int maxNum) {
        this.id = id;
        this.name = name;
        this.latinName = latinName;
        this.gender = gender;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.maxNum = maxNum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }
}
