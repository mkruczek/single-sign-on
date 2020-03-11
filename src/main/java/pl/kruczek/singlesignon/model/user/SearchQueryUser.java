package pl.kruczek.singlesignon.model.user;

import lombok.Getter;

import java.util.Collections;
import java.util.Set;

@Getter
public class SearchQueryUser {

    Set<String> ids;
    Set<String> usernames;
    Set<String> firstnames;
    Set<String> lastnames;
    Set<String> scores;
    Set<String> actives;
    Set<String> emails;
    Set<String> roles;

    public SearchQueryUser(String ids, String usernames, String firstnames, String lastnames, String scores, String actives, String emails, String roles) {
        this.ids = ids != null ? Set.of(ids.split(",")) : Collections.<String>emptySet();
        this.usernames = usernames != null ? Set.of(usernames.split(",")) : Collections.<String>emptySet();
        this.firstnames = firstnames != null ? Set.of(firstnames.split(",")) : Collections.<String>emptySet();
        this.lastnames = lastnames != null ? Set.of(lastnames.split(",")) : Collections.<String>emptySet();
        this.scores = scores != null ? Set.of(scores.split(",")) : Collections.<String>emptySet();
        this.actives = actives != null ? Set.of(actives.split(",")) : Collections.<String>emptySet();
        this.emails = emails != null ? Set.of(emails.split(",")) : Collections.<String>emptySet();
        this.roles = roles != null ? Set.of(roles.split(",")) : Collections.<String>emptySet();
    }

    public boolean isEmpty() {
        return this.ids.isEmpty()
                && this.usernames.isEmpty()
                && this.firstnames.isEmpty()
                && this.lastnames.isEmpty()
                && this.scores.isEmpty()
                && this.actives.isEmpty()
                && this.emails.isEmpty()
                && this.roles.isEmpty();
    }

}

