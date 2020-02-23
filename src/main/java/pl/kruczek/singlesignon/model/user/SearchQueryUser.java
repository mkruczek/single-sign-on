package pl.kruczek.singlesignon.model.user;

import lombok.Getter;

import java.util.Set;

@Getter
public class SearchQueryUser {

//    SearchQueryUser sq = new SearchQueryUser(id, username, firstname, lastname, score, active, email, roles);


    Set<String> ids;
    Set<String> usernames;
    Set<String> firstnames;
    Set<String> lastnames;
    Set<String> scores;
    Set<String> actives;
    Set<String> emails;
    Set<String> roles;

    public SearchQueryUser(String ids, String usernames, String firstnames, String lastnames, String scores, String actives, String emails, String roles) {
        this.ids = ids != null ? Set.of(ids.split(",")) : null;
        this.usernames = usernames != null ? Set.of(usernames.split(",")) : null;
        this.firstnames = firstnames != null ? Set.of(firstnames.split(",")) : null;
        this.lastnames = lastnames != null ? Set.of(lastnames.split(",")) : null;
        this.scores = scores != null ? Set.of(scores.split(",")) : null;
        this.actives = actives != null ? Set.of(actives.split(",")) : null;
        this.emails = emails != null ? Set.of(emails.split(",")) : null;
        this.roles = roles != null ? Set.of(roles.split(",")) : null;
    }

    public boolean isEmpty() {
        return this.ids == null
                && this.usernames == null
                && this.firstnames == null
                && this.lastnames == null
                && this.scores == null
                && this.actives == null
                && this.emails == null
                && this.roles == null;
    }

}

