import matplotlib.pyplot as plt
import pandas as pd
import os

def process_files(file_names):
    mean_fitness_per_generation = []
    best_fitness_per_generation = []
    
    # Reading data from each file and compute mean and best fitness per generation
    for filename in file_names:
        data = pd.read_csv(filename, sep='\s+', skiprows=1, header=None)
        mean_fitness_per_generation.append(data.groupby(0)[1].mean())
        best_fitness_per_generation.append(data.groupby(0)[2].mean())
    
    # Calculating average mean and best fitness per generation across all generations
    avg_mean_fitness = pd.concat(mean_fitness_per_generation).groupby(level=0).mean()
    avg_best_fitness = pd.concat(best_fitness_per_generation).groupby(level=0).mean()
    
    # Calculating standard deviation for mean and best fitness per generation
    std_mean_fitness = pd.concat(mean_fitness_per_generation).groupby(level=0).std()
    std_best_fitness = pd.concat(best_fitness_per_generation).groupby(level=0).std()
    
    return avg_mean_fitness, avg_best_fitness, std_mean_fitness, std_best_fitness

def generate_results_table(avg_mean_fitness, avg_best_fitness, std_mean_fitness, std_best_fitness):
    # Create a DataFrame for the results table
    results_df = pd.DataFrame({
        'Generation': avg_mean_fitness.index,
        'Average Mean Fitness': avg_mean_fitness.values,
        'Standard Deviation Mean Fitness': std_mean_fitness.values,
        'Average Best Fitness': avg_best_fitness.values,
        'Standard Deviation Best Fitness': std_best_fitness.values
    })
    
    return results_df

# Specify the directory containing the files
directory = r'H:\BrockUniversityThridYear\Second Semester\COSC 4P82(GP)\Assignment 2\NewStats\#1_90_10_10_elites'

# Create a list of file paths by concatenating the directory with file names
file_names = [os.path.join(directory, f'job.{i}.binout.stat') for i in range(10)]

# Print the file path for verification
print("File path: ", directory)

# Average mean and best fitness per generation
avg_mean_fitness, avg_best_fitness, std_mean_fitness, std_best_fitness = process_files(file_names)

# Generate the results table
results_table = generate_results_table(avg_mean_fitness, avg_best_fitness, std_mean_fitness, std_best_fitness)

# Print the results table
print("Results Table:")
print(results_table.to_string(index=False))

# Graph plot
plt.figure(figsize=(10, 6))

# Plotting average fitness per generation across 10 runs with standard deviation
plt.errorbar(avg_mean_fitness.index, avg_mean_fitness, label='Average Mean Fitness', linestyle='-', marker='o', color='blue')
plt.errorbar(avg_best_fitness.index, avg_best_fitness, label='Average Best Fitness', linestyle='--', marker='x', color='orange')

# Labels
plt.xlabel('Generation')
plt.ylabel('Fitness')
plt.title('Average Population Fitness and Best Fitness per Generation with Standard Deviation')
plt.legend()

# Plot Display
plt.grid(True)
plt.tight_layout()
plt.show()
