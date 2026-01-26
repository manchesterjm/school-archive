# Claude Session Notes - School Computer

> **SETUP:** Place this file in your home directory (`~/CLAUDE.md`) and clone the school-archive repo.
>
> ```bash
> cd ~
> git clone https://github.com/manchesterjm/school-archive.git
> cp ~/school-archive/Claude_Config/CLAUDE.md ~/CLAUDE.md
> ```

> **RULES:**
> 1. **Coding standards are mandatory:** Follow SOLID/SOFA principles, BDD→TDD workflow, 80%+ test coverage, 100% mutations killed (equivalent mutants and crash-inducing mutations excepted), linter 10/10. Fix code to pass tests (never rewrite tests to pass broken code). Fix security issues (bypass only with verified false positive AND user approval).
> 2. **Fix errors, don't just report them:** When tests fail or errors occur, investigate and fix the code. Task is incomplete until all tests pass.
> 3. **Documentation workflow:** At the START of each task, consider whether it will need documentation. At the END, document what was done.

---

## Quick Reference Index

Reference files located in `~/school-archive/Claude_Config/`

### Read on Demand (When Topic Comes Up)

| When Asked About... | Read This File |
|---------------------|----------------|
| **Writing code, tests, development** | `~/school-archive/Claude_Config/Coding_Standards_Reference.md` **(READ BEFORE CODING)** |
| Master's degree, MSCS, UCCS courses | `~/school-archive/Claude_Config/MSCS_Degree_Reference.md` |

### Archived School Courses

Non-CS/math courses are archived in `~/school-archive/`:
- ANTH3380 (Evolutionary Medicine & Health)
- ENG122 (English Composition)
- GEOG105 (Geography)
- GEY112 (Geology)
- HUM115 (World Mythology)
- SOC101 (Sociology)
- TCID2090 (Tech Writing & Presentation)

---

## GitHub Repositories

| Project | Repository |
|---------|------------|
| **School Archive** | https://github.com/manchesterjm/school-archive |
| **Simulations** (physics, SOC, Monte Carlo) | https://github.com/manchesterjm/my_simulations |
| **Minesweeper ML** (AC-3 + DQN agents) | https://github.com/manchesterjm/minesweeper_ml_project |

---

## Syncing with Home Computer

To get latest updates from home:
```bash
cd ~/school-archive && git pull
```
