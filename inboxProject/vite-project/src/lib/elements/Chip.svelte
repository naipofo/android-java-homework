<script lang="ts">
    export let name: string;
    export let closable: boolean = false;
</script>

<div>
    <span>{name}</span>
    {#if closable}
        <span>X</span>
    {/if}
</div>

<style>
    div{
        font-size: .8rem;
        font-weight: 600;
        display: flex;
        border-radius: 8px;
        padding: 4px;
        color: white;
        background-color: var(--dark);
        text-transform: uppercase;

        margin-right: 8px;
    }
</style>