package services;

import lombok.NonNull;
import models.Problem;
import models.User;

public interface ISolver {
    Boolean solve(@NonNull final User user, @NonNull final Problem problem);
}
