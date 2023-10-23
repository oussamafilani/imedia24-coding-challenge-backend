-- V1.0.3__Seed_Data.sql

-- Insert sample data into the products table
INSERT INTO products (sku, name, description, price, created_at, updated_at)
VALUES
    ('SKU001', 'Product 1', 'Description 1', 10.99, NOW(), NOW()),
    ('SKU002', 'Product 2', 'Description 2', 15.99, NOW(), NOW()),
    ('SKU003', 'Product 3', 'Description 3', 12.99, NOW(), NOW()),
    ('SKU004', 'Product 4', 'Description 4', 8.99, NOW(), NOW()),
    ('SKU005', 'Product 5', 'Description 5', 14.99, NOW(), NOW()),
    ('SKU006', 'Product 6', 'Description 6', 9.99, NOW(), NOW()),
    ('SKU007', 'Product 7', 'Description 7', 11.99, NOW(), NOW()),
    ('SKU008', 'Product 8', 'Description 8', 7.99, NOW(), NOW()),
    ('SKU009', 'Product 9', 'Description 9', 13.99, NOW(), NOW()),
    ('SKU010', 'Product 10', 'Description 10', 16.99, NOW(), NOW()),
    -- Add more product records
    ('SKU011', 'Product 11', 'Description 11', 18.99, NOW(), NOW()),
    ('SKU012', 'Product 12', 'Description 12', 19.99, NOW(), NOW()),
    ('SKU013', 'Product 13', 'Description 13', 17.99, NOW(), NOW());