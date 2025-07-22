# TODO

- Create a logger wrapper to be used to write in Log terminal also UI.
- Fix the issue with the EditText losing focus when the soft keyboard is opened. It might be related to the fact that we're trying to detect whether the soft keyboard is being displayed or not.
- Check the behavior of "Quadratic Form" when pressing S1 and S2 — they should remain visible if they were already visible.   Also, check the possibility in both "Quadratic Form" and "Primes List" that when you leave and come back, the previous results are still retained.
- Add the dotted version in the "Primes" section.
- Remove the Helper class and create the utils package then categorize the static methods like: utils.InputValidation, ...