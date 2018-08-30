package com.extensionrepository.service;

import com.extensionrepository.entity.Extension;
import org.kohsuke.github.GHIssueState;
import org.kohsuke.github.GitHub;

import java.io.IOException;
import java.util.Date;

public class GitHubService {
    private static final String GITHUB_URL = "https://github.com/";
    private static final String AUTH_KEY = "10ca3c23a20bdc3a74541182253e59890842e6ad";

    public static Extension fetchGithubInfo(Extension extension) {
        GitHub gitHub = null;
        try {
            gitHub = GitHub.connectUsingOAuth(AUTH_KEY);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String repositoryName = extension.getRepositoryLink().substring(GITHUB_URL.length());
        try {

            extension.setOpenIssues(gitHub.getRepository(repositoryName).getOpenIssueCount());
            extension.setPullRequests(gitHub.getRepository(repositoryName).getPullRequests(GHIssueState.ALL).size());
            extension.setLastCommit(gitHub.getRepository(repositoryName).getUpdatedAt());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return extension;
    }
}
