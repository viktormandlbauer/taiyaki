package at.technikum.taiyaki.backend.dto;


import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor

public class ReviewDto {

    private UUID id;

    @NotBlank
    private String reviewTitle;

    @NotBlank
    private String reviewText;

    @NotNull
    @Min(1)
    @Max(5)
    private int rating;

    public ReviewDto(UUID id, int rating, String reviewText, String reviewTitle) {
        this.id = id;
        this.rating = rating;
        this.reviewText = reviewText;
        this.reviewTitle = reviewTitle;
    }

    public ReviewDto(String reviewTitle, String reviewText, int rating) {
        this.reviewTitle = reviewTitle;
        this.reviewText = reviewText;
        this.rating = rating;
    }
}
