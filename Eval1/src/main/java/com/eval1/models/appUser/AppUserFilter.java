package com.eval1.models.appUser;

import custom.springutils.search.map.IgnoreMapping;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUserFilter {

    @IgnoreMapping
    private String keyWord;

    public String getFilterConditions() {
        StringBuilder filterConditions = new StringBuilder();
        filterConditions.append("keyWord");
        if (getKeyWord() != null) {
            filterConditions.append("=" + getKeyWord());
        }

        return filterConditions.toString().replace("%", "");
    }
}
