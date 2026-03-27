package com.meal;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestHeader;
import java.util.*;

@RestController
@RequestMapping("/api/meals")
@CrossOrigin(origins = "*")
public class MealController {

    private List<Meal> meals = new ArrayList<>(Arrays.asList(
        new Meal(1,  "Butter Chicken",   "non-veg", "indulgent", "🍛"),
        new Meal(2,  "Paneer Tikka",     "veg",     "indulgent", "🧆"),
        new Meal(3,  "Grilled Salmon",   "non-veg", "healthy",   "🐟"),
        new Meal(4,  "Caesar Salad",     "veg",     "healthy",   "🥗"),
        new Meal(5,  "Margherita Pizza", "veg",     "indulgent", "🍕"),
        new Meal(6,  "Chicken Biryani",  "non-veg", "indulgent", "🍚"),
        new Meal(7,  "Avocado Toast",    "veg",     "healthy",   "🥑"),
        new Meal(8,  "Egg Fried Rice",   "non-veg", "healthy",   "🍳"),
        new Meal(9,  "Dal Tadka",        "veg",     "healthy",   "🍲"),
        new Meal(10, "Masala Dosa",      "veg",     "healthy",   "🫓"),
        new Meal(11, "BBQ Ribs",         "non-veg", "indulgent", "🍖"),
        new Meal(12, "Mushroom Pasta",   "veg",     "indulgent", "🍝"),
        new Meal(13, "Chicken Sandwich", "non-veg", "healthy",   "🥪"),
        new Meal(14, "Fruit Bowl",       "veg",     "healthy",   "🍓"),
        new Meal(15, "Veggie Burger",    "veg",     "healthy",   "🥦")
    ));

    private int nextId = 16;

    // GET ALL
    @GetMapping("/all")
    public List<Meal> getAllMeals() {
        return meals;
    }

    // GET ONE BY ID
    @GetMapping("/{id}")
    public Meal getMealById(@PathVariable int id) {
        return meals.stream()
                    .filter(m -> m.getId() == id)
                    .findFirst()
                    .orElse(null);
    }

    // GET RANDOM
    @GetMapping("/random")
    public Meal getRandomMeal() {
        Random rand = new Random();
        return meals.get(rand.nextInt(meals.size()));
    }

    // GET FILTERED
    @GetMapping("/filter")
    public List<Meal> filterMeals(
        @RequestParam(required = false) String type,
        @RequestParam(required = false) String category
    ) {
        List<Meal> result = new ArrayList<>();
        for (Meal meal : meals) {
            boolean typeMatch     = (type == null     || meal.getType().equals(type));
            boolean categoryMatch = (category == null || meal.getCategory().equals(category));
            if (typeMatch && categoryMatch) result.add(meal);
        }
        if (result.isEmpty()) return result;
        Random rand = new Random();
        return List.of(result.get(rand.nextInt(result.size())));
    }

    // POST - ADD
    @PostMapping
    public Meal addMeal(@RequestBody Meal meal) {
        meal.setId(nextId++);
        meals.add(meal);
        return meal;
    }

    // PUT - UPDATE
    @PutMapping("/{id}")
    public Meal updateMeal(@PathVariable int id, @RequestBody Meal updated) {
        for (Meal meal : meals) {
            if (meal.getId() == id) {
                meal.setName(updated.getName());
                meal.setType(updated.getType());
                meal.setCategory(updated.getCategory());
                meal.setEmoji(updated.getEmoji());
                return meal;
            }
        }
        return null;
    }

    // DELETE
@DeleteMapping("/{id}")
public Map<String, String> deleteMeal(
    @PathVariable int id,
    @RequestHeader(value = "X-User-Role", defaultValue = "") String role
) {
    Map<String, String> res = new HashMap<>();
    if (!role.equals("admin")) {
        res.put("status",  "error");
        res.put("message", "Access denied. Admins only.");
        return res;
    }
    boolean removed = meals.removeIf(m -> m.getId() == id);
    res.put("status",  removed ? "success" : "error");
    res.put("message", removed ? "Meal deleted ✅" : "Meal not found ❌");
    return res;
}
}