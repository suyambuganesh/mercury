//package org.github.mercury.aggregator.jira;
//
//import java.io.IOException;
//import java.net.URI;
//import java.net.URISyntaxException;
//
//import org.springframework.stereotype.Component;
//
//import com.atlassian.jira.rest.client.api.JiraRestClient;
//import com.atlassian.jira.rest.client.api.domain.Issue;
//import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
//
//@Component
//public class JiraClient {
//
//    public Issue getCurrentSprint() throws URISyntaxException, IOException {
//        final AsynchronousJiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
//        Issue issue = null;
//        URI jiraUri = new URI("https://jira.eden.klm.com");
//        final JiraRestClient restClient = factory.createWithBasicHttpAuthentication(jiraUri, "X071817", "aug@2015");
//        try {
//            issue = restClient.getIssueClient().getIssue("LIVEAITIH-1516").claim();
//        }
//        finally {
//            // cleanup the restClient
//            restClient.close();
//        }
//        return issue;
//    }
//
//    public static void main(final String[] arg) {
//        JiraClient jiraClient = new JiraClient();
//        try {
//            System.out.println(jiraClient.getCurrentSprint());
//        }
//        catch (URISyntaxException | IOException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
// }
