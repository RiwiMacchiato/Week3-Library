# Contributing to Projects


## Table of Contents
  - [Commit Message Convention](#commit-message-convention)
  - [Branch Naming Guide](#branch-naming-guide)

## Commit Message Convention

Follow the conventional commit format:

```text
<type>(<scope>): <description>

[optional body]

[optional footer(s)]
```

**Types:**

- `feat` - A new feature
- `fix` - A bug fix
- `docs` - Documentation only changes
- `style` - Changes that do not affect the meaning of the code
- `refactor` - A code change that neither fixes a bug nor adds a feature
- `perf` - A code change that improves performance
- `test` - Adding missing tests or correcting existing tests
- `chore` - Changes to the build process or auxiliary tools

**Scope (optional):**

- `api` - API related changes
- `ui` - User interface changes
- `db` - Database related changes
- `config` - Configuration changes
- `auth` - Authentication related changes

**Examples:**

```text
feat(api): add CV upload endpoint
fix(db): resolve connection timeout issue
docs: update README with installation instructions
style(ui): improve button styling consistency
refactor(controller): optimize CV processing logic
perf(db): add database indexing for faster queries
test(model): add unit tests for CvModel
chore(deps): update express to version 5.1.0
```

### Detailed Commit Guidelines

**Good commit messages:**

- `feat(cv): implement PDF text extraction functionality`
- `fix(api): handle file upload errors gracefully`
- `docs(contributing): add feature naming conventions`
- `refactor(routes): simplify CV route handlers`

**Bad commit messages:**

- `update stuff`
- `fix bug`
- `changes`
- `work in progress`

### Best Practices

1. **Keep commits atomic** - Each commit should represent a single logical change
2. **Write descriptive messages** - Explain what and why, not just what
3. **Use imperative mood** - "Add feature" instead of "Added feature"
4. **Limit subject line to 50 characters** - Keep it concise
5. **Separate subject from body with blank line**
6. **Use body to explain motivation** - When necessary

## Branch Naming Guide

### Branch Naming Convention

When creating a new branch, use the following format:

```text
<type>/<short-description>
```

**Types:**

- `feature/` - New features or enhancements
- `bugfix/` - Bug fixes
- `hotfix/` - Critical fixes that need immediate attention
- `refactor/` - Code refactoring without functional changes
- `docs/` - Documentation updates
- `test/` - Adding or updating tests
- `chore/` - Maintenance tasks, dependency updates

**Examples:**

- `feature/user-authentication`
- `feature/cv-upload-functionality`
- `bugfix/database-connection-error`
- `hotfix/security-vulnerability`
- `refactor/controller-optimization`
- `docs/api-documentation`
- `test/unit-tests-for-models`
- `chore/update-dependencies`
