package org.github.mercury.aggregator.jira;

import java.util.List;

import net.rcarz.jiraclient.BasicCredentials;
import net.rcarz.jiraclient.Issue;
import net.rcarz.jiraclient.JiraException;
import net.rcarz.jiraclient.greenhopper.Backlog;
import net.rcarz.jiraclient.greenhopper.Epic;
import net.rcarz.jiraclient.greenhopper.GreenHopperClient;
import net.rcarz.jiraclient.greenhopper.Marker;
import net.rcarz.jiraclient.greenhopper.RapidView;
import net.rcarz.jiraclient.greenhopper.Sprint;
import net.rcarz.jiraclient.greenhopper.SprintIssue;
import net.rcarz.jiraclient.greenhopper.SprintReport;

public class JiraAgileClient {

    public static void main2(final String[] args) {

        BasicCredentials creds = new BasicCredentials("batman", "pow! pow!");
        net.rcarz.jiraclient.JiraClient jira = new net.rcarz.jiraclient.JiraClient("https://jira.example.com/jira", creds);
        GreenHopperClient gh = new GreenHopperClient(jira);

        try {
            /* Retrieve all Rapid Boards */
            List<RapidView> allRapidBoards = gh.getRapidViews();

            /* Retrieve a specific Rapid Board by ID */
            RapidView board = gh.getRapidView(123);

            /* Print the name of all current and past sprints */
            List<Sprint> sprints = board.getSprints();
            for (Sprint s : sprints) {
                System.out.println(s);
            }

            /*
             * Get the sprint report, print the sprint start date and the number of completed issues
             */
            SprintReport sr = board.getSprintReport(sprints.get(0));
            System.out.println(sr.getSprint().getStartDate());
            System.out.println(sr.getCompletedIssues().size());

            /* Get backlog data */
            Backlog backlog = board.getBacklogData();

            /* Print epic names */
            for (Epic e : backlog.getEpics()) {
                System.out.println(e);
            }

            /* Print all issues in the backlog */
            for (SprintIssue si : backlog.getIssues()) {
                System.out.println(si);
            }

            /* Print the names of sprints that haven't started yet */
            for (Marker m : backlog.getMarkers()) {
                System.out.println(m);
            }

            /* Get the first issue on the backlog and add a comment */
            SprintIssue firstIssue = backlog.getIssues().get(0);
            Issue jiraIssue = firstIssue.getJiraIssue();
            jiraIssue.addComment("a comment!");
        }
        catch (JiraException ex) {
            System.err.println(ex.getMessage());

            if (ex.getCause() != null) {
                System.err.println(ex.getCause().getMessage());
            }
        }
    }

}
