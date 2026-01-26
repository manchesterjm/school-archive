# Coding Standards Reference

## Professional Context

**As of December 2025:** Bachelor's degree in Computer Science completed. All code must now reflect professional software development standards.

**Starting Spring 2025:** Master's degree in Computer Science. Code quality expectations are elevated to graduate/professional level.

### What This Means

| Aspect | Expectation |
|--------|-------------|
| **Code Quality** | Production-ready, not "student code" |
| **Documentation** | Complete docstrings, comments, and clear logic |
| **Architecture** | SOLID for classes, SOFA for functions, proper separation of concerns |
| **Maintainability** | Code others (or future self) can understand and modify |
| **Complexity** | Cyclomatic complexity under 10, no code smells |
| **Testing** | BDD → TDD workflow, 80%+ coverage, 100% tests pass, 100% mutations killed (equivalent mutants and crash-inducing mutations excepted), fuzz testing |
| **Linting** | Pylint 10/10 (Python), C linter 10/10 |
| **Test Integrity** | Fix code to pass tests (not vice versa), fix security issues (bypass only with user approval) |
| **Error Handling** | When errors are found, FIX them - don't just report them |
| **No Shortcuts** | No "it works" hacks - do it right the first time |

All code written from this point forward represents the work of a **software development professional**, not a student learning the basics. This standard applies to personal projects, coursework, and any code committed to repositories.

---

## SOLID Principles (Class Design)

SOLID is a set of five design principles for writing maintainable, extensible object-oriented code.

### S - Single Responsibility Principle (S.R.P.)
> A class should have only one reason to change.

Each class should have one job, one responsibility. If a class handles multiple concerns, changes to one concern may break the other.

**Example:**
```python
# Bad - handles both user data and persistence
class User:
    def __init__(self, name, email):
        self.name = name
        self.email = email

    def save_to_database(self):
        # Database logic mixed with user logic
        pass

# Good - separate concerns
class User:
    def __init__(self, name, email):
        self.name = name
        self.email = email

class UserRepository:
    def save(self, user: User):
        # Database logic isolated here
        pass
```

---

### O - Open-Closed Principle (O.C.P.)
> Software entities should be open for extension, but closed for modification.

Add new functionality by extending code (inheritance, composition), not by modifying existing working code.

**Example:**
```python
# Bad - must modify Shape class to add new shapes
class Shape:
    def area(self):
        if self.type == "circle":
            return 3.14 * self.radius ** 2
        elif self.type == "rectangle":
            return self.width * self.height

# Good - extend without modifying
class Shape:
    def area(self) -> float:
        raise NotImplementedError

class Circle(Shape):
    def __init__(self, radius):
        self.radius = radius

    def area(self) -> float:
        return 3.14 * self.radius ** 2

class Rectangle(Shape):
    def __init__(self, width, height):
        self.width = width
        self.height = height

    def area(self) -> float:
        return self.width * self.height
```

---

### L - Liskov Substitution Principle (L.S.P.)
> Objects of a superclass should be replaceable with objects of a subclass without affecting correctness.

Subclasses must honor the contract of their parent class. If code works with a base class, it should work identically with any subclass.

**Example:**
```python
# Bad - Square violates Rectangle's behavior
class Rectangle:
    def set_width(self, w): self.width = w
    def set_height(self, h): self.height = h

class Square(Rectangle):  # Violates LSP
    def set_width(self, w):
        self.width = w
        self.height = w  # Unexpected side effect!

# Good - don't force inheritance that doesn't fit
class Shape:
    def area(self) -> float:
        raise NotImplementedError

class Rectangle(Shape):
    def __init__(self, width, height):
        self.width = width
        self.height = height

class Square(Shape):
    def __init__(self, side):
        self.side = side
```

---

### I - Interface Segregation Principle (I.S.P.)
> Clients should not be forced to depend on interfaces they do not use.

Many specific interfaces are better than one general-purpose interface. Don't force classes to implement methods they don't need.

**Example:**
```python
# Bad - forces all workers to implement eat()
class Worker:
    def work(self): pass
    def eat(self): pass  # Robots don't eat!

# Good - separate interfaces
class Workable:
    def work(self): pass

class Eatable:
    def eat(self): pass

class Human(Workable, Eatable):
    def work(self): ...
    def eat(self): ...

class Robot(Workable):
    def work(self): ...
```

---

### D - Dependency Inversion Principle (D.I.P.)
> High-level modules should not depend on low-level modules. Both should depend on abstractions.

Depend on interfaces/abstract classes, not concrete implementations. This makes code more flexible and testable.

**Example:**
```python
# Bad - high-level depends on low-level
class EmailNotifier:
    def send(self, message): ...

class OrderService:
    def __init__(self):
        self.notifier = EmailNotifier()  # Tight coupling

# Good - depend on abstraction
class Notifier:
    def send(self, message): pass

class EmailNotifier(Notifier):
    def send(self, message): ...

class SMSNotifier(Notifier):
    def send(self, message): ...

class OrderService:
    def __init__(self, notifier: Notifier):
        self.notifier = notifier  # Inject dependency
```

---

## SOFA Principles (Function Design)

SOFA is a set of guidelines for better function design, complementing the broader SOLID principles for classes.

### S - Short
Functions should be **concise and easy to read**, not excessively long.

**Guidelines:**
- Functions should fit on one screen (~25-40 lines max)
- If a function is getting long, it's doing too much - split it
- Each function should be quickly understandable

**Example (C):**
```c
/* Bad - too long, doing multiple things */
void do_everything(int x) {
    // 100+ lines of mixed logic
}

/* Good - short and focused */
static void shuffle(char *array, int n) {
    for (int i = n - 1; i > 0; i--) {
        int j = rand() % (i + 1);
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
```

---

### O - One Thing (Single Responsibility)
Each function should have a **single, clear purpose**, making it easier to understand and test.

**Guidelines:**
- Function name should describe exactly what it does
- If you need "and" in the name, split the function
- Side effects should be obvious or avoided

**Example (Python):**
```python
# Bad - does multiple things
def validate_and_generate_passwords(length, count):
    # validates input AND generates passwords AND updates UI
    pass

# Good - single responsibility
def validate_length(length_str: str) -> int | None:
    """Validate password length, return None if invalid."""
    try:
        length = int(length_str)
        if 8 <= length <= 60:
            return length
    except ValueError:
        pass
    messagebox.showwarning("Invalid Input", "Password length must be between 8 and 60")
    return None
```

---

### F - Few Arguments
Functions should take a **limited number of parameters**, reducing complexity and improving predictability.

**Guidelines:**
- Aim for 0-3 parameters
- 4+ parameters is a code smell - consider grouping into a struct/class
- Use constants or global config for common values

**Example (C):**
```c
/* Bad - too many arguments */
HWND create_control(HWND parent, const char *text, int x, int y,
                    int width, int height, int id, DWORD style,
                    HFONT font, const char *class_name);

/* Good - reduced arguments using constants and globals */
static HWND create_button(HWND parent, const char *text, int x, int y,
                          int width, int height, int id, DWORD style) {
    HWND hwnd = CreateWindowA("BUTTON", text,
        WS_CHILD | WS_TABSTOP | style,
        x, y, width, height, parent, (HMENU)(INT_PTR)id,
        GetModuleHandle(NULL), NULL);
    SendMessage(hwnd, WM_SETFONT, (WPARAM)g_hUIFont, TRUE);
    return hwnd;
}
```

---

### A - Abstraction Level Consistency
All operations within a function should occur at the **same level of abstraction**, preventing mixing high-level logic with low-level details.

**Guidelines:**
- Don't mix "what" (high-level) with "how" (low-level)
- High-level functions call mid-level functions
- Mid-level functions call low-level functions
- Low-level functions do the actual work

**Example (Python):**
```python
# Bad - mixed abstraction levels
def on_generate(self):
    length = int(self.length_var.get())  # Low-level
    if length < 8 or length > 60:        # Low-level validation
        messagebox.showwarning(...)       # UI detail
    # ... more mixed code

# Good - consistent abstraction
def _on_generate(self):
    """Handle generate button click."""  # High-level
    length = validate_length(self.length_var.get())  # Mid-level
    if length is None:
        return

    count = validate_count(self.count_var.get())     # Mid-level
    if count is None:
        return

    punct = normalize_punctuation(self.punct_var.get())  # Mid-level
    passwords = generate_password_list(count, length, punct)  # Mid-level

    self._display_passwords(passwords)    # High-level
    self._show_output_controls(count)     # High-level
```

---

## Code Organization

### File Structure
Organize code into logical sections with clear headers:

```c
// ============================================================================
// Constants
// ============================================================================

// ============================================================================
// Low-level Utilities
// ============================================================================

// ============================================================================
// Mid-level Functions
// ============================================================================

// ============================================================================
// High-level Functions / Event Handlers
// ============================================================================

// ============================================================================
// Application Entry Point
// ============================================================================
```

### Abstraction Layers

| Layer | Description | Example |
|-------|-------------|---------|
| **Low-level** | Basic operations, utilities | `shuffle()`, `char_in_array()` |
| **Mid-level** | Business logic, validation | `validate_length()`, `generate_single_password()` |
| **High-level** | Orchestration, UI events | `on_generate()`, `init_window_layout()` |

---

## UI Standards

### Dynamic Sizing
- **Never hardcode window sizes** - calculate from content
- Use margins consistently (e.g., `MARGIN = 30`)
- Output areas should fill available width between margins
- Window height should adjust to fit content

### Constants
Define layout constants at the top of the file:
```c
#define MARGIN 30
#define ROW_HEIGHT 55
#define BTN_HEIGHT 45
#define BTN_WIDTH 150
```

```python
MARGIN = 30
FONT_SIZE = 18
```

### Font Consistency
- Use a single font size for all UI elements
- Define fonts as constants or in a setup function
- UI font (Segoe UI) for labels, buttons, inputs
- Mono font (Consolas) for code/password output

---

## Documentation Standards

All code must be well-documented using three levels of documentation:

### Docstrings (Function/Module Level)
Every function and module should have a docstring explaining its purpose.

**Python:**
```python
def validate_length(length_str: str) -> int | None:
    """Validate password length, return None if invalid.

    Args:
        length_str: String representation of desired password length

    Returns:
        Validated integer length, or None if validation fails
    """
```

**C:**
```c
/*
 * Validate password length from edit control.
 * Returns validated length, or -1 if invalid.
 * Shows error message and sets focus on failure.
 */
static int get_validated_int(HWND hwndEdit, int min, int max,
                              const char *error_msg, HWND hwndParent);
```

### Block Comments (Section/Logic Level)
Use block comments to explain complex logic, algorithms, or to separate code sections.

**Section Headers:**
```c
// ============================================================================
// Password Generation (Mid-level)
// ============================================================================
```

**Algorithm Explanation:**
```c
/*
 * Fisher-Yates shuffle algorithm.
 * Iterates backwards through array, swapping each element
 * with a randomly selected element from the remaining unshuffled portion.
 * Time complexity: O(n), Space complexity: O(1)
 */
static void shuffle(char *array, int n) {
    ...
}
```

**Python Section Headers:**
```python
# ============================================================================
# Input Validation (Mid-level)
# ============================================================================
```

### Inline Comments (Line Level)
Use inline comments sparingly to explain non-obvious code.

**Good - explains WHY:**
```c
int fill_count = length - used_count;  // Remaining chars needed after required ones
```

```python
chars = sample(NUMBERS, 2)  # Guarantee at least 2 digits in password
```

**Bad - explains WHAT (obvious from code):**
```c
int i = 0;  // Set i to 0
```

### When to Comment

| Situation | Comment Type | Required? |
|-----------|--------------|-----------|
| Every function | Docstring | **Yes** |
| Code sections | Block header | **Yes** |
| Complex algorithms | Block explanation | **Yes** |
| Non-obvious logic | Inline | Yes |
| Magic numbers | Inline | Yes |
| Workarounds/hacks | Inline + why | **Yes** |
| Self-explanatory code | None | No |

### Comment Style Guide

1. **Be concise** - Comments should be brief but complete
2. **Explain WHY, not WHAT** - Code shows what, comments explain why
3. **Keep comments updated** - Outdated comments are worse than none
4. **Use proper grammar** - Complete sentences, proper capitalization
5. **Document edge cases** - Note any assumptions or limitations

**Example of well-documented function:**
```c
/*
 * Generate a single password with guaranteed character diversity.
 *
 * Ensures password contains at least 2 characters from each category:
 * numbers, lowercase, uppercase, and special characters.
 * Remaining characters are randomly selected from unused chars.
 */
static void generate_single_password(char *result, int length, const char *punct) {
    char used[64], remaining[128], password[64];

    // Sample required characters (2 from each category)
    int used_count = sample_required_chars(used, punct);

    // Build pool of remaining unused characters
    int remaining_count = build_remaining_chars(remaining, used, used_count, punct);

    shuffle(remaining, remaining_count);

    // Combine required + random remaining to reach desired length
    memcpy(password, used, used_count);
    int fill_count = length - used_count;
    if (fill_count > 0 && fill_count <= remaining_count) {
        memcpy(&password[used_count], remaining, fill_count);
    }
    password[length] = '\0';

    // Final shuffle so required chars aren't always at the start
    shuffle(password, length);
    strcpy(result, password);
}
```

---

## Naming Conventions

### Functions
- Lowercase with underscores (C and Python)
- Verb + noun pattern: `create_button`, `validate_length`
- Private methods prefixed with underscore (Python): `_on_generate`
- Static functions for internal use (C): `static void shuffle(...)`

### Constants
- UPPERCASE with underscores: `DEFAULT_PUNCT`, `MARGIN`
- Prefix with module/scope if needed: `ID_GENERATE`

### Variables
- Lowercase with underscores
- Descriptive names: `password_count` not `pc`
- Loop variables can be short: `i`, `j`

---

## Why SOFA Matters

| Principle | Benefit |
|-----------|---------|
| **Short** | Easier to read, review, and debug |
| **One Thing** | Simpler to test and modify without breaking other parts |
| **Few Arguments** | Reduces coupling, improves predictability |
| **Abstraction** | Cleaner mental model, easier to navigate codebase |

---

## Cyclomatic Complexity

Cyclomatic complexity measures the number of independent paths through code. It's calculated by counting decision points (if, while, for, case, &&, ||, etc.) plus one.

### Complexity Targets

| Complexity | Risk Level | Action |
|------------|------------|--------|
| **1-10** | Simple | Good - maintainable code |
| **11-20** | Moderate | Consider refactoring |
| **21-50** | High | **Code smell** - refactor required |
| **51+** | Extreme | Untestable - must refactor |

### Guidelines

- **Target complexity under 10** for all functions
- Each branch/decision adds 1 to complexity
- High complexity = hard to test, hard to maintain
- Break complex functions into smaller, focused functions

**Example:**
```python
# Bad - complexity ~8 (multiple nested conditions)
def process_order(order):
    if order.is_valid:
        if order.has_stock:
            if order.payment_confirmed:
                if order.address_verified:
                    ship_order(order)
                else:
                    request_address(order)
            else:
                request_payment(order)
        else:
            backorder(order)
    else:
        reject_order(order)

# Good - complexity ~2 per function (early returns, extracted functions)
def process_order(order):
    if not order.is_valid:
        return reject_order(order)
    if not order.has_stock:
        return backorder(order)
    if not order.payment_confirmed:
        return request_payment(order)
    if not order.address_verified:
        return request_address(order)
    return ship_order(order)
```

---

## Code Smells

Code smells are patterns that indicate potential problems. They make code harder to read, maintain, and test.

### Common Code Smells

| Smell | Description | Solution |
|-------|-------------|----------|
| **Long Method** | Function too long to understand quickly | Extract smaller functions |
| **Long Parameter List** | Too many arguments (4+) | Use object/struct to group |
| **Duplicate Code** | Same logic repeated | Extract to shared function |
| **Dead Code** | Unused variables, unreachable code | Delete it |
| **Magic Numbers** | Unexplained literal values | Use named constants |
| **God Class** | Class does too much | Split into focused classes |
| **Feature Envy** | Method uses another class's data excessively | Move method to that class |
| **Data Clumps** | Same group of data appears together | Create a class/struct |
| **Primitive Obsession** | Using primitives instead of small objects | Create value objects |
| **Switch Statements** | Long switch/if-else chains | Use polymorphism |
| **Speculative Generality** | Code for "future" needs that may never come | YAGNI - delete it |
| **Comments** | Excessive comments explaining bad code | Refactor code to be self-documenting |

### Legacy Code Definition

> "Legacy code is code without tests." — Michael Feathers

Code without tests is dangerous to modify because you can't verify you haven't broken anything.

---

## Testing Standards

> **Testing is not optional.** Tests are the safety net that allows code to be modified, refactored, and extended without fear. Code without tests is a liability in production.

### Test Integrity Rules (MANDATORY)

These rules are **non-negotiable** and must be followed in all circumstances:

#### 1. Fix Errors, Don't Just Report Them

When tests fail or errors are found:
- **DO:** Investigate the root cause and fix the code
- **DON'T:** Just report "an error was found" and move on

```
❌ WRONG: "The test failed. Here's the error message."
✅ RIGHT: "The test failed because X. I've fixed the code by doing Y. All tests now pass."
```

#### 2. Fix Code to Pass Tests, Not Tests to Pass Code

Tests define the expected behavior. When a test fails:
- **DO:** Fix the implementation code to meet the test's expectations
- **DON'T:** Modify the test to accept the broken behavior

```python
# Original test (CORRECT - defines expected behavior)
def test_validate_length_rejects_negative():
    assert validate_length("-5") is None

# Implementation fails the test
def validate_length(s):
    return int(s)  # Returns -5, not None!

# ❌ WRONG: Change test to match broken code
def test_validate_length_rejects_negative():
    assert validate_length("-5") == -5  # NO! This hides the bug!

# ✅ RIGHT: Fix the code to match the test
def validate_length(s):
    try:
        length = int(s)
        if length < 0:
            return None  # Fixed!
        return length
    except ValueError:
        return None
```

**The only exception:** If the test itself has a bug (wrong expected value, testing the wrong thing), fix the test AND document why it was wrong.

#### 3. Fix Security Issues, Don't Bypass Security Tests

When security tests fail:
- **DO:** Fix the vulnerability in the code
- **DON'T:** Disable the security test, add exceptions, or work around it

```python
# Security test catches SQL injection vulnerability
def test_no_sql_injection():
    malicious_input = "'; DROP TABLE users; --"
    # Should not execute raw SQL
    assert not contains_sql_keywords(sanitize_input(malicious_input))

# ❌ WRONG: Skip the test or add exception
@pytest.mark.skip("Too hard to fix")  # NO!
def test_no_sql_injection(): ...

# ❌ WRONG: Bypass in code
if not SECURITY_TESTING:  # NO!
    execute_raw_sql(user_input)

# ✅ RIGHT: Fix the vulnerability
def execute_query(user_input):
    sanitized = sanitize_input(user_input)
    cursor.execute("SELECT * FROM users WHERE name = ?", (sanitized,))
```

**Exception - False Positives (REQUIRES USER APPROVAL):**

The **only** time a security warning may be bypassed is when:
1. The warning has been **verified and validated** as a false positive (not an actual vulnerability)
2. The user has been **notified** with a clear explanation of why it's a false positive
3. The user has **explicitly approved** the bypass

```python
# Example: Security tool flags a hash comparison as "timing attack vulnerable"
# but the hash is for non-sensitive display purposes only

# Step 1: Explain to user
"""
Security tool flagged timing attack vulnerability in display_hash_preview().
This is a FALSE POSITIVE because:
- The hash is only used for UI display (showing first 8 chars)
- No authentication or secrets are involved
- An attacker gains nothing from timing information here

Request: Approve bypass of this security warning?
"""

# Step 2: WAIT for user approval before proceeding

# Step 3: If approved, document the bypass with justification
# pylint: disable=timing-attack  # APPROVED: Non-sensitive display only, see issue #123
def display_hash_preview(hash_value):
    return hash_value[:8]
```

**If uncertain whether it's a real vulnerability or false positive → treat it as real and fix it.**

#### 4. All Tests Must Pass Before Completion

A task is **not complete** until:
- All existing tests pass
- All new tests pass
- No tests are skipped or disabled
- No security warnings are suppressed

```bash
# This is NOT acceptable:
pytest
# 47 passed, 3 failed, 2 skipped

# This IS acceptable:
pytest
# 52 passed
```

#### Summary: The Testing Hierarchy

```
┌─────────────────────────────────────────────────────────┐
│  1. Tests define CORRECT behavior                       │
│  2. Code must CONFORM to tests                          │
│  3. Security issues are FIXED (bypass only if verified  │
│     false positive AND user approves)                   │
│  4. Errors are FIXED, not reported                      │
│  5. Task is INCOMPLETE until all tests pass             │
│  6. When uncertain → treat as real vulnerability        │
└─────────────────────────────────────────────────────────┘
```

---

### Why Testing is Critical

| Without Tests | With Tests |
|---------------|------------|
| Fear of making changes | Confidence to refactor |
| Bugs discovered in production | Bugs caught before deployment |
| Manual verification required | Automated verification |
| "It worked on my machine" | Consistent across environments |
| Technical debt accumulates | Code stays maintainable |

**The cost of fixing a bug increases exponentially the later it's found:**
- During development: 1x cost
- During testing: 10x cost
- In production: 100x cost

---

### Testing Levels (V-Model)

| Level | What It Tests | Who Writes | When to Run |
|-------|---------------|------------|-------------|
| **Unit Testing** | Individual functions/methods in isolation | Developer | Every commit |
| **Integration Testing** | Components working together | Developer/QA | Every PR/merge |
| **System Testing** | Complete system end-to-end | QA Team | Before release |
| **Acceptance Testing** | User requirements satisfied | Customer/PM | Before deployment |

---

### FIRST Principles for Unit Tests

| Principle | Meaning | Why It Matters |
|-----------|---------|----------------|
| **F - Fast** | Tests run quickly (milliseconds) | Slow tests don't get run |
| **I - Independent** | Tests don't depend on each other | Can run in any order, parallelize |
| **R - Repeatable** | Same result every time, any environment | No flaky tests |
| **S - Self-validating** | Pass/fail automatically | No manual inspection needed |
| **T - Timely** | Written with the code (or before in TDD) | Tests exist when needed |

---

### Behavior-Driven Development (BDD)

> **Start with WHAT the user needs, then figure out HOW to build it.**

BDD bridges the gap between business requirements and technical implementation by writing tests in natural language that stakeholders can understand.

**The BDD Flow:**
1. **Discovery** - Collaborate with stakeholders to understand requirements
2. **Formulation** - Write scenarios in Gherkin (Given-When-Then)
3. **Automation** - Implement step definitions that execute the scenarios
4. **Implementation** - Write code to make scenarios pass (TDD)

---

### Gherkin Syntax

Gherkin is a structured natural language for writing executable specifications.

**Feature File Structure:**
```gherkin
# features/password_generation.feature

Feature: Password Generation
  As a user
  I want to generate secure passwords
  So that I can protect my accounts

  Background:
    Given the password generator is open

  Scenario: Generate password with default settings
    When I click the Generate button
    Then I should see 5 passwords displayed
    And each password should be 16 characters long

  Scenario: Generate password with custom length
    Given I set the password length to 24
    When I click the Generate button
    Then each password should be 24 characters long

  Scenario: Password contains required character types
    When I generate a password
    Then the password should contain at least 2 numbers
    And the password should contain at least 2 lowercase letters
    And the password should contain at least 2 uppercase letters
    And the password should contain at least 2 special characters

  Scenario Outline: Validate password length boundaries
    When I set the password length to <length>
    And I click the Generate button
    Then I should see <result>

    Examples:
      | length | result                    |
      | 7      | an error message          |
      | 8      | passwords displayed       |
      | 60     | passwords displayed       |
      | 61     | an error message          |
```

**Gherkin Keywords:**

| Keyword | Purpose |
|---------|---------|
| **Feature** | Describes the feature being tested |
| **Scenario** | A specific test case |
| **Given** | Preconditions / initial state |
| **When** | Action / trigger |
| **Then** | Expected outcome / assertion |
| **And/But** | Continue previous keyword |
| **Background** | Steps run before each scenario |
| **Scenario Outline** | Parameterized scenario |
| **Examples** | Data table for scenario outline |

---

### Python BDD with pytest-bdd

```bash
pip install pytest-bdd
```

**Step Definitions:**
```python
# tests/step_defs/test_password_generation.py

from pytest_bdd import scenarios, given, when, then, parsers
from passwords import validate_length, generate_single_password

# Load all scenarios from feature file
scenarios('../features/password_generation.feature')

# Fixtures for shared state
@pytest.fixture
def context():
    return {}

# Step implementations
@given("the password generator is open")
def generator_open(context):
    context['punct'] = list("@#$%&?*")

@given(parsers.parse("I set the password length to {length:d}"))
def set_length(context, length):
    context['length'] = length

@when("I click the Generate button")
def click_generate(context):
    length = context.get('length', 16)
    validated = validate_length(str(length))
    if validated:
        context['passwords'] = [
            generate_single_password(validated, context['punct'])
            for _ in range(5)
        ]
        context['error'] = None
    else:
        context['passwords'] = []
        context['error'] = "Invalid length"

@when("I generate a password")
def generate_one(context):
    context['password'] = generate_single_password(16, context['punct'])

@then(parsers.parse("I should see {count:d} passwords displayed"))
def verify_count(context, count):
    assert len(context['passwords']) == count

@then(parsers.parse("each password should be {length:d} characters long"))
def verify_length(context, length):
    for pwd in context['passwords']:
        assert len(pwd) == length

@then(parsers.parse("the password should contain at least {count:d} numbers"))
def verify_numbers(context, count):
    digits = sum(1 for c in context['password'] if c.isdigit())
    assert digits >= count

@then("I should see an error message")
def verify_error(context):
    assert context['error'] is not None

@then("I should see passwords displayed")
def verify_success(context):
    assert len(context['passwords']) > 0
```

**Run BDD tests:**
```bash
pytest tests/step_defs/ -v
```

---

### BDD to TDD Workflow

1. **Write Feature File** (Gherkin) - Define behavior in business terms
2. **Write Step Definitions** - Map Gherkin to Python
3. **Run Tests** - They fail (RED)
4. **TDD the Implementation** - Write unit tests and code
5. **Run Tests** - BDD scenarios pass (GREEN)
6. **Refactor** - Clean up while keeping everything green

```
                    ┌─────────────────────────────────────┐
                    │         BDD (Outer Loop)            │
                    │  Feature → Steps → Run → Pass/Fail  │
                    └─────────────────────────────────────┘
                                     │
                                     ▼
                    ┌─────────────────────────────────────┐
                    │         TDD (Inner Loop)            │
                    │    Test → Code → Refactor           │
                    └─────────────────────────────────────┘
```

**Benefits of BDD + TDD:**
- Requirements are executable documentation
- Stakeholders can read and validate tests
- Tests describe behavior, not implementation
- Clear traceability from requirements to code

---

### Test-Driven Development (TDD)

TDD is a discipline where tests are written **before** the implementation code.

**The Red-Green-Refactor Cycle:**

1. **RED** - Write a failing test for the feature you want
2. **GREEN** - Write the minimum code to make the test pass
3. **REFACTOR** - Clean up the code while keeping tests green

**Benefits of TDD:**
- Forces you to think about requirements first
- Guarantees high test coverage
- Produces testable, modular code
- Provides documentation of expected behavior
- Catches bugs immediately

**Example TDD Flow:**
```python
# Step 1: RED - Write failing test first
def test_validate_length_rejects_negative():
    result = validate_length("-5")
    assert result is None

# Step 2: GREEN - Write minimum code to pass
def validate_length(length_str: str) -> int | None:
    try:
        length = int(length_str)
        if length < 0:
            return None
        return length
    except ValueError:
        return None

# Step 3: REFACTOR - Improve while keeping green
def validate_length(length_str: str) -> int | None:
    """Validate password length, return None if invalid."""
    try:
        length = int(length_str)
        if 8 <= length <= 60:
            return length
    except ValueError:
        pass
    return None
```

---

### Test Coverage

| Coverage Type | Description | Target |
|---------------|-------------|--------|
| **C0 (Statement)** | Every line executed at least once | 80%+ |
| **C1 (Branch)** | Every branch (if/else) taken | 70%+ for critical code |
| **C2 (Path)** | Every possible path through code | Impractical for most code |

**Measure coverage with pytest:**
```bash
pytest --cov=mymodule --cov-report=html tests/
```

**Coverage is a minimum, not a goal.** 100% coverage doesn't mean bug-free code - it means every line ran, not that every scenario was tested correctly.

---

### What to Test

| Always Test | Don't Bother Testing |
|-------------|---------------------|
| Public API functions | Private helper methods (test through public API) |
| Business logic | Getters/setters with no logic |
| Edge cases and boundaries | Framework/library code |
| Error handling paths | Trivial one-liners |
| Input validation | UI layout (unless critical) |
| Security-sensitive code | Configuration files |

---

### Arrange-Act-Assert (AAA) Pattern

Every test should have three distinct sections:

```python
def test_generate_password_meets_length_requirement():
    # Arrange - Set up test data and preconditions
    desired_length = 16
    punct = list("@#$%")

    # Act - Execute the code under test
    password = generate_single_password(desired_length, punct)

    # Assert - Verify the expected outcome
    assert len(password) == desired_length
```

---

### Testing Edge Cases

For every function, consider these categories:

| Category | Examples |
|----------|----------|
| **Empty/Zero** | Empty string, empty list, 0, None |
| **One** | Single character, single element, 1 |
| **Boundary** | Min-1, Min, Max, Max+1 |
| **Invalid Types** | String instead of int, None instead of object |
| **Special Characters** | Unicode, newlines, quotes, null bytes |
| **Large Inputs** | Very long strings, huge numbers |

**Example - Testing Boundaries:**
```python
def test_validate_length_at_minimum():
    assert validate_length("8") == 8  # Min boundary

def test_validate_length_below_minimum():
    assert validate_length("7") is None  # Below min

def test_validate_length_at_maximum():
    assert validate_length("60") == 60  # Max boundary

def test_validate_length_above_maximum():
    assert validate_length("61") is None  # Above max
```

---

### Test Naming Conventions

Use descriptive names that explain what is being tested:

```python
# Pattern: test_<function>_<scenario>_<expected_result>

# Good - clear and descriptive
def test_validate_length_with_negative_returns_none(): ...
def test_validate_length_with_valid_input_returns_integer(): ...
def test_generate_password_contains_required_character_types(): ...

# Bad - vague and unhelpful
def test_validate(): ...
def test_1(): ...
def test_password(): ...
```

---

### Mocking and Test Doubles

When testing code that has external dependencies (database, API, file system), use mocks to isolate the unit under test.

```python
from unittest.mock import Mock, patch

# Mock an external API call
def test_fetch_user_handles_api_error():
    with patch('mymodule.requests.get') as mock_get:
        mock_get.return_value.status_code = 500

        result = fetch_user(123)

        assert result is None
        mock_get.assert_called_once()

# Mock a database
def test_save_user_calls_repository():
    mock_repo = Mock()
    service = UserService(repository=mock_repo)

    service.save_user(User("Alice"))

    mock_repo.save.assert_called_once()
```

---

### Running Tests (Python/pytest)

```bash
# Run all tests
pytest

# Run with verbose output
pytest -v

# Run specific test file
pytest tests/test_passwords.py

# Run tests matching a pattern
pytest -k "validate"

# Run with coverage report
pytest --cov=mymodule --cov-report=term-missing

# Stop on first failure
pytest -x

# Run tests in parallel (requires pytest-xdist)
pytest -n auto
```

---

### Test File Organization

```
project/
├── src/
│   └── passwords.py
├── tests/
│   ├── __init__.py
│   ├── test_passwords.py      # Unit tests for passwords.py
│   ├── test_integration.py    # Integration tests
│   └── conftest.py            # Shared fixtures
└── pytest.ini                 # pytest configuration
```

**conftest.py for shared fixtures:**
```python
import pytest

@pytest.fixture
def sample_punctuation():
    """Provide standard punctuation for password tests."""
    return list("@#$%&?*")

@pytest.fixture
def valid_password_length():
    """Provide a valid password length."""
    return 16
```

---

### Continuous Integration

Tests should run automatically on every push/PR:

```yaml
# .github/workflows/tests.yml
name: Tests
on: [push, pull_request]
jobs:
  test:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v4
      - uses: actions/setup-python@v5
        with:
          python-version: '3.13'
      - run: pip install pytest pytest-cov
      - run: pytest --cov=src --cov-fail-under=80
```

**Key CI principles:**
- Tests must pass before merging
- Coverage must not decrease
- Failed tests block deployment

---

### Mutation Testing

> **The question isn't "do my tests run?" - it's "do my tests actually catch bugs?"**

Mutation testing verifies that your tests are meaningful by introducing small bugs (mutations) into your code and checking if tests catch them.

**How it works:**
1. Tool creates "mutants" - copies of code with small changes
2. Each mutant has one modification (e.g., `>` becomes `>=`, `+` becomes `-`)
3. Tests run against each mutant
4. If tests pass with the mutant, the test suite is **weak** - it missed a bug

| Result | Meaning |
|--------|---------|
| **Killed** | Tests caught the mutation (good!) |
| **Survived** | Tests missed the bug (need better tests) |
| **Timeout** | Mutation caused infinite loop |
| **No Coverage** | Mutated code wasn't even tested |

**Mutation Score = Killed Mutants / Total Mutants**

**Target: 100% mutations killed** (equivalent mutants and crash-inducing mutations excepted)

**Python tools:**
```bash
# Install mutmut
pip install mutmut

# Run mutation testing
mutmut run --paths-to-mutate=src/

# View results
mutmut results

# See surviving mutants (tests that need improvement)
mutmut show 1
```

**Example - A surviving mutant reveals weak tests:**
```python
# Original code
def is_valid_age(age):
    return age >= 18  # Mutant changes to: age > 18

# Weak test - doesn't catch the mutation
def test_valid_age():
    assert is_valid_age(25) == True   # Passes with >= AND >
    assert is_valid_age(10) == False  # Passes with >= AND >

# Strong test - catches the boundary mutation
def test_valid_age_at_boundary():
    assert is_valid_age(18) == True   # Fails with > 18, catches mutant!
    assert is_valid_age(17) == False
```

---

### Fuzz Testing

> **Find bugs humans wouldn't think to test for.**

Fuzz testing (fuzzing) automatically generates random, unexpected, or malformed inputs to find crashes, hangs, and security vulnerabilities.

**Why fuzz testing matters:**
- Humans think of "normal" inputs - fuzzers find edge cases
- Discovers buffer overflows, injection vulnerabilities, crashes
- Finds inputs that violate assumptions you didn't know you made

**Python tools:**
```bash
# Install hypothesis (property-based testing / fuzzing)
pip install hypothesis
```

**Example - Hypothesis fuzz testing:**
```python
from hypothesis import given, strategies as st

# Hypothesis generates hundreds of random test cases automatically
@given(st.text())
def test_validate_length_never_crashes(s):
    """Fuzz test: validate_length should handle ANY string input."""
    result = validate_length(s)
    assert result is None or isinstance(result, int)

@given(st.integers())
def test_generate_password_length_always_correct(length):
    """Fuzz test: password length should match request (if valid)."""
    if 8 <= length <= 60:
        punct = list("@#$%")
        password = generate_single_password(length, punct)
        assert len(password) == length

@given(st.lists(st.characters(), min_size=2))
def test_generate_password_uses_provided_punctuation(punct):
    """Fuzz test: passwords should only use provided special chars."""
    password = generate_single_password(16, punct)
    # Verify special chars in password are from provided set
    special_in_password = [c for c in password if not c.isalnum()]
    for c in special_in_password:
        assert c in punct
```

**Hypothesis strategies for common types:**
```python
from hypothesis import strategies as st

st.integers()              # Any integer
st.integers(min_value=0)   # Non-negative integers
st.floats()                # Any float (including NaN, inf)
st.text()                  # Any string (including unicode, empty)
st.binary()                # Random bytes
st.lists(st.integers())    # Lists of integers
st.dictionaries(st.text(), st.integers())  # Dict with string keys
st.none() | st.integers()  # None or integer
```

**What fuzz testing finds that manual testing misses:**
- Empty strings, None values
- Unicode characters (emoji, RTL text, null bytes)
- Extremely large numbers
- Negative numbers where only positive expected
- Special characters that break parsing
- Strings that look like code injection

---

### Testing Tools by Language

The same principles apply across all languages - only the tools differ.

#### Python

| Purpose | Tool | Install |
|---------|------|---------|
| Unit Testing | `pytest` | `pip install pytest` |
| Coverage | `pytest-cov` | `pip install pytest-cov` |
| BDD | `pytest-bdd` | `pip install pytest-bdd` |
| Mutation | `mutmut` | `pip install mutmut` |
| Fuzz/Property | `hypothesis` | `pip install hypothesis` |

```bash
# Install all Python testing tools
pip install pytest pytest-cov pytest-bdd mutmut hypothesis

# Run tests with coverage
pytest --cov=src --cov-report=term-missing --cov-fail-under=80

# Mutation testing
mutmut run --paths-to-mutate=src/
```

---

#### Java

| Purpose | Tool | Notes |
|---------|------|-------|
| Unit Testing | JUnit 5 | Standard Java testing framework |
| Coverage | JaCoCo | Integrates with Maven/Gradle |
| BDD | Cucumber | Gherkin support for Java |
| Mutation | PITest | Industry standard mutation testing |
| Fuzz/Property | jqwik | Property-based testing for Java |

**Maven pom.xml dependencies:**
```xml
<dependencies>
    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <version>5.10.0</version>
        <scope>test</scope>
    </dependency>

    <!-- Cucumber BDD -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit-platform-engine</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
    </dependency>

    <!-- jqwik property-based testing -->
    <dependency>
        <groupId>net.jqwik</groupId>
        <artifactId>jqwik</artifactId>
        <version>1.8.0</version>
        <scope>test</scope>
    </dependency>
</dependencies>

<build>
    <plugins>
        <!-- JaCoCo Coverage -->
        <plugin>
            <groupId>org.jacoco</groupId>
            <artifactId>jacoco-maven-plugin</artifactId>
            <version>0.8.11</version>
        </plugin>

        <!-- PITest Mutation Testing -->
        <plugin>
            <groupId>org.pitest</groupId>
            <artifactId>pitest-maven</artifactId>
            <version>1.15.0</version>
            <dependencies>
                <dependency>
                    <groupId>org.pitest</groupId>
                    <artifactId>pitest-junit5-plugin</artifactId>
                    <version>1.2.0</version>
                </dependency>
            </dependencies>
        </plugin>
    </plugins>
</build>
```

**Java test examples:**
```java
// Unit test with JUnit 5
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PasswordValidatorTest {
    @Test
    void validateLength_withValidInput_returnsLength() {
        int result = PasswordValidator.validateLength("16");
        assertEquals(16, result);
    }

    @Test
    void validateLength_belowMinimum_returnsNegativeOne() {
        int result = PasswordValidator.validateLength("7");
        assertEquals(-1, result);
    }
}

// Property-based test with jqwik
import net.jqwik.api.*;

class PasswordGeneratorProperties {
    @Property
    void passwordLengthAlwaysMatchesRequest(@ForAll @IntRange(min = 8, max = 60) int length) {
        String password = PasswordGenerator.generate(length);
        assertThat(password.length()).isEqualTo(length);
    }

    @Property
    void passwordNeverCrashesOnAnyInput(@ForAll String input) {
        // Should handle any input without throwing
        assertDoesNotThrow(() -> PasswordValidator.validateLength(input));
    }
}
```

**Java BDD with Cucumber:**
```java
// src/test/resources/features/password.feature uses same Gherkin syntax

// Step definitions
import io.cucumber.java.en.*;

public class PasswordSteps {
    private int length;
    private String password;

    @Given("I set the password length to {int}")
    public void setLength(int length) {
        this.length = length;
    }

    @When("I generate a password")
    public void generatePassword() {
        this.password = PasswordGenerator.generate(length);
    }

    @Then("the password should be {int} characters long")
    public void verifyLength(int expected) {
        assertEquals(expected, password.length());
    }
}
```

**Run Java tests:**
```bash
# Run tests with coverage
mvn test jacoco:report

# Run mutation testing
mvn pitest:mutationCoverage

# Run only Cucumber BDD tests
mvn test -Dcucumber.filter.tags="@password"
```

---

#### C

| Purpose | Tool | Notes |
|---------|------|-------|
| Unit Testing | Unity / Check / CUnit | Lightweight C test frameworks |
| Coverage | gcov + lcov | GCC built-in coverage |
| BDD | Cgreen | BDD-style assertions for C |
| Mutation | Mull / Dextool | LLVM-based mutation testing |
| Fuzz | AFL++ / libFuzzer | Industry-standard C fuzzers |

**Unity test example (recommended for simplicity):**
```c
// test_password.c
#include "unity.h"
#include "password_generator.h"

void setUp(void) {}
void tearDown(void) {}

void test_validate_length_valid_input(void) {
    TEST_ASSERT_EQUAL(16, validate_length("16"));
}

void test_validate_length_below_minimum(void) {
    TEST_ASSERT_EQUAL(-1, validate_length("7"));
}

void test_validate_length_above_maximum(void) {
    TEST_ASSERT_EQUAL(-1, validate_length("61"));
}

void test_validate_length_non_numeric(void) {
    TEST_ASSERT_EQUAL(-1, validate_length("abc"));
}

void test_password_contains_required_chars(void) {
    char password[64];
    generate_password(password, 16, "@#$%");

    int has_digit = 0, has_lower = 0, has_upper = 0, has_special = 0;
    for (int i = 0; password[i]; i++) {
        if (isdigit(password[i])) has_digit = 1;
        else if (islower(password[i])) has_lower = 1;
        else if (isupper(password[i])) has_upper = 1;
        else has_special = 1;
    }

    TEST_ASSERT_TRUE(has_digit);
    TEST_ASSERT_TRUE(has_lower);
    TEST_ASSERT_TRUE(has_upper);
    TEST_ASSERT_TRUE(has_special);
}

int main(void) {
    UNITY_BEGIN();
    RUN_TEST(test_validate_length_valid_input);
    RUN_TEST(test_validate_length_below_minimum);
    RUN_TEST(test_validate_length_above_maximum);
    RUN_TEST(test_validate_length_non_numeric);
    RUN_TEST(test_password_contains_required_chars);
    return UNITY_END();
}
```

**C coverage with gcov:**
```bash
# Compile with coverage flags
gcc -fprofile-arcs -ftest-coverage -o test_password test_password.c password_generator.c unity.c

# Run tests
./test_password

# Generate coverage report
gcov password_generator.c
lcov --capture --directory . --output-file coverage.info
genhtml coverage.info --output-directory coverage_html
```

**C fuzz testing with AFL++:**
```c
// fuzz_password.c - AFL++ harness
#include <stdio.h>
#include <string.h>
#include "password_generator.h"

int main(void) {
    char input[256];
    if (fgets(input, sizeof(input), stdin)) {
        input[strcspn(input, "\n")] = 0;  // Remove newline
        validate_length(input);  // Should never crash
    }
    return 0;
}
```

```bash
# Compile with AFL++
afl-gcc -o fuzz_password fuzz_password.c password_generator.c

# Run fuzzer
mkdir input output
echo "16" > input/seed.txt
afl-fuzz -i input -o output ./fuzz_password
```

---

#### JavaScript/TypeScript

| Purpose | Tool | Install |
|---------|------|---------|
| Unit Testing | Jest / Vitest | `npm install jest` |
| Coverage | Built into Jest | `jest --coverage` |
| BDD | Cucumber.js | `npm install @cucumber/cucumber` |
| Mutation | Stryker | `npm install @stryker-mutator/core` |
| Fuzz/Property | fast-check | `npm install fast-check` |

```bash
# Install all JS testing tools
npm install --save-dev jest @cucumber/cucumber @stryker-mutator/core fast-check

# Run tests with coverage
jest --coverage --coverageThreshold='{"global":{"lines":80}}'

# Mutation testing
npx stryker run
```

**JavaScript property-based testing with fast-check:**
```javascript
import fc from 'fast-check';
import { validateLength, generatePassword } from './passwords.js';

describe('Password Generator Properties', () => {
    it('never crashes on any string input', () => {
        fc.assert(fc.property(fc.string(), (input) => {
            const result = validateLength(input);
            return result === null || typeof result === 'number';
        }));
    });

    it('password length always matches request', () => {
        fc.assert(fc.property(
            fc.integer({ min: 8, max: 60 }),
            (length) => {
                const password = generatePassword(length);
                return password.length === length;
            }
        ));
    });
});
```

---

### Testing Requirements Summary

| Requirement | Target | Python | Java | C | JS/TS |
|-------------|--------|--------|------|---|-------|
| **BDD Scenarios** | All features | pytest-bdd | Cucumber | Cgreen | Cucumber.js |
| **Line Coverage** | 80%+ | pytest-cov | JaCoCo | gcov | Jest |
| **Mutation Score** | 100% | mutmut | PITest | Mull | Stryker |
| **Fuzz Testing** | Public functions | hypothesis | jqwik | AFL++ | fast-check |

---

### Project Structure (Language-Agnostic)

```
project/
├── src/                         # Source code
├── features/                    # Gherkin BDD feature files (all languages)
│   └── password_generation.feature
├── tests/
│   ├── unit/                    # Unit tests
│   ├── step_defs/               # BDD step definitions
│   ├── fuzz/                    # Fuzz test harnesses
│   └── fixtures/                # Test data
└── [build config]               # pom.xml, Makefile, package.json, etc.
```

---

### Development Workflow (All Languages)

1. **Write Gherkin feature file** (define behavior in plain language)
2. **Write step definitions** (map Gherkin to code in your language)
3. **Write unit tests** for implementation details (TDD)
4. **Implement code** to pass all tests
5. **Run fuzz/property tests** to find edge cases
6. **Run mutation testing** to verify test quality
7. **Refactor** while keeping all tests green
8. **Check coverage** meets 80%+ threshold

---

## Refactoring

Refactoring is restructuring code to improve readability and maintainability **without changing behavior**.

### When to Refactor

- Before adding new features (clean the area first)
- After getting tests passing (Red-Green-Refactor)
- When code smells are detected
- When complexity exceeds thresholds

### Refactoring Techniques

| Technique | When to Use |
|-----------|-------------|
| **Extract Method** | Long function, repeated code |
| **Rename** | Unclear names |
| **Inline** | Method body is as clear as name |
| **Move Method** | Method belongs in another class |
| **Replace Magic Number** | Unexplained literal values |
| **Introduce Parameter Object** | Too many parameters |
| **Replace Conditional with Polymorphism** | Long switch/if-else chains |

### The Refactoring Cycle

1. **Run tests** - Ensure everything passes
2. **Make one small change** - Single refactoring step
3. **Run tests** - Verify nothing broke
4. **Repeat** - Continue with next improvement

**Never refactor and add features in the same commit.**

---

## Architecture Patterns

### MVC (Model-View-Controller)

| Component | Responsibility |
|-----------|----------------|
| **Model** | Data, business logic, database access |
| **View** | UI rendering, display |
| **Controller** | Handles user input, coordinates Model and View |

### REST API Design

| HTTP Method | Purpose | Example |
|-------------|---------|---------|
| **GET** | Retrieve data | `GET /users/123` |
| **POST** | Create new resource | `POST /users` |
| **PUT** | Update entire resource | `PUT /users/123` |
| **PATCH** | Partial update | `PATCH /users/123` |
| **DELETE** | Remove resource | `DELETE /users/123` |

---

## Reference Implementations

Both password generator implementations demonstrate SOFA principles:

| File | Language | Location |
|------|----------|----------|
| `password_generator.c` | C (Win32) | `D:\Scripts\` |
| `passwords.py` | Python (Tkinter) | `D:\OneDrive\Desktop\CS3080\` |

---

*Last updated: 2025-12-20*
